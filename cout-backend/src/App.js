import { useState } from 'react';
import './App.css';
import db, { timestamp } from './firebase';


function App() {
  const [header,setHeader] = useState();
  const [headerArray,setHeaderArray] = useState([]);
  const [question,setQuestion] = useState();
  const [problemStatement,setProblemSTatement] = useState();
  const [footer_1,setFooter_1] = useState("");
  const [footer_1Array,setFooter_1Array] = useState([]);
  const [publicTestCase,setPublicTestCase] = useState()
  const [privateTestCase,setPrivateTestCase] = useState();
  const [privateTestCaseOutput,setPrivateTestCaseOutput] = useState();
  
 
  const [footer_2,setFooter_2] = useState("");
  const [footer_2Array,setFooter_2Array] = useState([]);
  var testCases = []
  const getData = (e) => {
    e.preventDefault();
    
    testCases.length = 0;
    testCases.push(publicTestCase)
    testCases.push(privateTestCase)
          if(headerArray!=null&&question!=null&&footer_1Array!=null&&testCases!=null&&footer_2Array!=null)
          {  
            // db.collection("topics").doc("8Tlfgy2v1kd9603RN1Iy").collection("questions").add({ /*For c++*/ */
           db.collection("topics").doc("ixtYtqFAEBHaAoImxfMj").collection("questions").add({  /*For Java */
             "0_problemStatement" : problemStatement,
              "1_header" : headerArray,
              "2_question": question,
              "3_answer_1": footer_1Array,
              "4_testcases": testCases,
              "5_answer_2": footer_2Array,
              "6privateTestCaseOutput": privateTestCaseOutput, 
                           
              "timestamp": timestamp,
            });
            alert("Successfully Added")
            setPrivateTestCaseOutput(null);
            setPublicTestCase(null);
            setPrivateTestCase(null);
            setProblemSTatement(null);
            setHeaderArray([]);
            setFooter_1Array([]);
            setFooter_2Array([]);
          }
          else {
            alert("All fields are Required")
          }

  }
  
  return (
    <div className="app">
      <h1>COUT BACKEND SERVER</h1>
      <div>
      <form className="form">
        

        
          <div className = "block">
          <label><b>Problem Statement</b></label> 
            <textarea  placeholder = "Detail Problem Statement" value={problemStatement} onChange={e => {
              setProblemSTatement(e.target.value);
              }}  ></textarea>
          </div>
          <div className = "block">
            <label><b>HEADER</b></label> 
            <textarea placeholder = {"#include<iostream> \nusing namespace std; \nint functionName() \n{"}  value={header} onChange={e => {
              setHeader(e.target.value);
              setHeaderArray(e.target.value.split("\n"));
              }}  ></textarea>
          </div>
          <div className = "block">
            <label ><b>QUESTION</b></label> 
            <textarea placeholder = {"/*What is to be done*/"}  value={question} onChange={e => setQuestion(e.target.value)}  ></textarea>
          </div>
          <div className = "block">
            <label ><b>FOOTER_1</b></label> 
            <textarea  placeholder = {"} \nint main() \n{ \n"} value={footer_1} onChange={e => {setFooter_1(e.target.value);setFooter_1Array(e.target.value.split("\n"))}}></textarea>
          </div>
          <div className = "block">
            <label><b>PUBLIC TEST CASE</b></label> 
            <textarea  placeholder = {"cout<<..."} value={publicTestCase} onChange={e => setPublicTestCase(e.target.value)}  ></textarea>
          </div>
          <div className = "block">
            <label><b>PRIVATE TEST CASE</b></label> 
            <textarea  placeholder = {"cout<<..."} value={privateTestCase} onChange={e => setPrivateTestCase(e.target.value)}  ></textarea>
          </div>
          <div className = "block">
            <label><b>FOOTER_2</b></label> 
            <textarea  placeholder = {"return 0; \n}"} value={footer_2} onChange={e => {setFooter_2(e.target.value);setFooter_2Array(e.target.value.split("\n"))}}  ></textarea>
          </div>
          <div className = "block">

            <label><b>PRIVATE TESTCASE OUTPUT</b></label> 
            <textarea  placeholder = {"Paste the Private TestCaseOutput Exactly from terminal."} value={privateTestCaseOutput} onChange={e => {
              setPrivateTestCaseOutput(e.target.value);
              }}  ></textarea>
          </div>
          <button className = "Button" onClick = {getData}>SUBMIT</button>
            
      </form>
      </div>
    </div>
  );
}

export default App;
/*
  #include<iostream>
using namespace std;
int add(int a,int b)
{
/Edit the code below/
/*return a+b;
}
int main()
{
cout<<add(20,30);
cout<<add(20,-20);
return 0;
}

// var request = require('request');

          // var program = {
          //     script : header+question+footer_1+publicTestCase+footer_2,
          //     language: "cpp",
          //     versionIndex: "0",
          //     clientId: "YourClientID",
          //     clientSecret:"YourClientSecret"
          // };
          // fetch (`https://api.jdoodle.com/v1/execute/${program}`).then(res=>res.json()).then((result)=>{
          //   console.log(result)
          // },
          // (error) => {
          //   console.log(error)
          // })
          // request({
          //     url: 'https://api.jdoodle.com/v1/execute',
          //     method: "POST",
          //     json: program
          // },
          // function (error, response, body) {
          //     console.log('error:', error);
          //     console.log('statusCode:', response && response.statusCode);
          //     console.log('body:', body);
          // });
*/