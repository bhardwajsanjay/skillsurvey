<!DOCTYPE html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Skills Gap Analysis Tool - Self Registration Form</title>
 
<style type="text/css">
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed,
figure, figcaption, footer, header, hgroup,
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}

article, aside, details, figcaption, figure,
footer, header, hgroup, menu, nav, section {
  display: block;
}

body {
  line-height: 1;
}

ol, ul {
  list-style: none;
}

blockquote, q {
  quotes: none;
}

blockquote:before, blockquote:after,
q:before, q:after {
  content: '';
  content: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
}

.about {
  margin: 70px auto 40px;
  padding: 8px;
  width: 260px;
  font: 10px/18px 'Lucida Grande', Arial, sans-serif;
  color: #666;
  text-align: center;
  text-shadow: 0 1px rgba(255, 255, 255, 0.25);
  background: #eee;
  background: rgba(250, 250, 250, 0.8);
  border-radius: 4px;
  background-image: -webkit-linear-gradient(top, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.1));
  background-image: -moz-linear-gradient(top, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.1));
  background-image: -o-linear-gradient(top, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.1));
  background-image: linear-gradient(to bottom, rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.1));
  -webkit-box-shadow: inset 0 1px rgba(255, 255, 255, 0.3), inset 0 0 0 1px rgba(255, 255, 255, 0.1), 0 0 6px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 1px rgba(255, 255, 255, 0.3), inset 0 0 0 1px rgba(255, 255, 255, 0.1), 0 0 6px rgba(0, 0, 0, 0.2);
}
.about a {
  color: #333;
  text-decoration: none;
  border-radius: 2px;
  -webkit-transition: background 0.1s;
  -moz-transition: background 0.1s;
  -o-transition: background 0.1s;
  transition: background 0.1s;
}
.about a:hover {
  text-decoration: none;
  background: #fafafa;
  background: rgba(255, 255, 255, 0.7);
}

.about-links {
  height: 30px;
}
.about-links > a {
  float: left;
  width: 50%;
  line-height: 30px;
  font-size: 12px;
}

.about-author {
  margin-top: 5px;
}
.about-author > a {
  padding: 1px 3px;
  margin: 0 -1px;
}

/*
 * Copyright (c) 2012-2013 Thibaut Courouble
 * http://www.cssflow.com
 *
 * Licensed under the MIT License:
 * http://www.opensource.org/licenses/mit-license.php
 */

/*-->Sumi: changed the background color to DDDDDD<--*/
body {
  font: 13px/20px 'Lucida Grande', Tahoma, Verdana, sans-serif;
  color: #404040;
  background: #dddddd;
}

.container {
  margin: 280px auto;
  width: 640px;
}

.login {
	position: relative;
	margin: 0 auto;
	padding: 20px 20px 20px;
	width: 500px;
	background: white;
	border-radius: 3px;
	-webkit-box-shadow: 0 0 200px rgba(255, 255, 255, 0.5), 0 1px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0 0 200px rgba(255, 255, 255, 0.5), 0 1px 2px rgba(0, 0, 0, 0.3);
	height: 380px;
}
.login:before {
  content: '';
  position: absolute;
  top: -8px;
  right: -8px;
  bottom: -8px;
  left: -8px;
  z-index: -1;
  background: rgba(0, 0, 0, 0.08);
  border-radius: 4px;
}
.login h1 {
  margin: -20px -20px 21px;
  line-height: 40px;
  font-size: 15px;
  font-weight: bold;
  color: #555;
  text-align: center;
  text-shadow: 0 1px white;
  background: #f3f3f3;
  border-bottom: 1px solid #cfcfcf;
  border-radius: 3px 3px 0 0;
  background-image: -webkit-linear-gradient(top, whiteffd, #eef2f5);
  background-image: -moz-linear-gradient(top, whiteffd, #eef2f5);
  background-image: -o-linear-gradient(top, whiteffd, #eef2f5);
  background-image: linear-gradient(to bottom, whiteffd, #eef2f5);
  -webkit-box-shadow: 0 1px whitesmoke;
  box-shadow: 0 1px whitesmoke;
}
.login p {
  margin: 20px 0 0;
}
.login p:first-child {
  margin-top: 0;
}
.login input[type=text], .login input[type=password] {
  width: 278px;
}
.login p.remember_me {
  float: left;
  line-height: 31px;
}
.login p.remember_me label {
  font-size: 12px;
  color: #777;
  cursor: pointer;
}
.login p.remember_me input {
  position: relative;
  bottom: 1px;
  margin-right: 4px;
  vertical-align: middle;
}
.login p.submit {
  text-align: right;
}

.login-help {
  margin: 20px 0;
  font-size: 11px;
  color: white;
  text-align: center;
  text-shadow: 0 1px #2a85a1;
}
.login-help a {
  color: #cce7fa;
  text-decoration: none;
}
.login-help a:hover {
  text-decoration: underline;
}

:-moz-placeholder {
  color: black !important;
  font-size: 13px;
}

::-webkit-input-placeholder {
  color: grey;
  font-size: 13px;
}

input {
  font-family: 'Lucida Grande', Tahoma, Verdana, sans-serif;
  font-size: 14px;
}

input[type=text], input[type=password] {
  margin: 5px;
  padding: 0 10px;
  width: 200px;
  height: 34px;
  color: #404040;
  background: white;
  border: 1px solid;
  border-color: #c4c4c4 #d1d1d1 #d4d4d4;
  border-radius: 2px;
  outline: 5px solid #eff4f7;
  -moz-outline-radius: 3px;
  -webkit-box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.12);
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.12);
}
input[type=text]:focus, input[type=password]:focus {
  border-color: #7dc9e2;
  outline-color: #dceefc;
  outline-offset: 0;
}

input[type=submit] {
  padding: 0 18px;
  height: 29px;
  font-size: 12px;
  font-weight: bold;
  color: #527881;
  text-shadow: 0 1px #e3f1f1;
  background: #cde5ef;
  border: 1px solid;
  border-color: #b4ccce #b3c0c8 #9eb9c2;
  border-radius: 16px;
  outline: 0;
  -webkit-box-sizing: content-box;
  -moz-box-sizing: content-box;
  box-sizing: content-box;
  background-image: -webkit-linear-gradient(top, #edf5f8, #cde5ef);
  background-image: -moz-linear-gradient(top, #edf5f8, #cde5ef);
  background-image: -o-linear-gradient(top, #edf5f8, #cde5ef);
  background-image: linear-gradient(to bottom, #edf5f8, #cde5ef);
  -webkit-box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
  box-shadow: inset 0 1px white, 0 1px 2px rgba(0, 0, 0, 0.15);
}
input[type=submit]:active {
  background: #cde5ef;
  border-color: #9eb9c2 #b3c0c8 #b4ccce;
  -webkit-box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 0 3px rgba(0, 0, 0, 0.2);
}

.lt-ie9 input[type=text], .lt-ie9 input[type=password] {
  line-height: 34px;
}


#apDiv1 {
	position:absolute;
	width:499px;
	height:49px;
	z-index:1;
	top: 247px;
}
</style>


<script type="text/javascript">

function submitForm(){
	
	if(validateForm()){
		document.frm.action="./public/adduser";
		 document.frm.submit();
	
	}else{
		return false;
	}
	
}
function validateForm()
{
var isValidation = false;

    if(document.frm.username.value=="")
    {
     document.getElementById("lbl-user-id").innerHTML ="UserID is mandatory. Please enter.";
     document.getElementById("lbl-user-id").style.color="Red";
     document.frm.username.focus();
     isValidation = false;
     return false;
    }else{
    	
    	document.frm.username.focus();
    	if(document.frm.username.value.length>8){
    		 document.getElementById("lbl-user-id").innerHTML ="UserID should have maximum 8 characters. Please enter.";
       	  document.getElementById("lbl-user-id").style.color="Red";
       	  isValidation = false;
       	  return false;
    	}
        if(document.frm.username.value.substring(0,5).toLowerCase()=="admin"){
        	  document.getElementById("lbl-user-id").innerHTML ="UserID can't have a prefix of admin or report. Please choose a different UserID.";
        	  document.getElementById("lbl-user-id").style.color="Red";
        	  isValidation = false;
        	  return false;
        }else{
        	document.getElementById("lbl-user-id").innerHTML ="";
        	isValidation=true;
        }
        if(document.frm.username.value.substring(0,6).toLowerCase()=="report"){
      	  document.getElementById("lbl-user-id").innerHTML ="UserID can't have a prefix of admin or report. Please choose a different UserID.";
      	  document.getElementById("lbl-user-id").style.color="Red";
      	isValidation= false;
      	return false;
      }else{
    	  document.getElementById("lbl-user-id").innerHTML ="";
    	  isValidation = true;
    	  
      }
       
    }
    if(document.frm.password1.value==""){
    	document.frm.password1.focus();
    	document.getElementById("lbl-password").innerHTML ="Password is mandatory. Please enter.";
    	document.getElementById("lbl-password").style.color="Red";
    	isValidation= false;
    	return false;
    }else{
    	if(document.frm.password1.value.length>8){
    		document.frm.password1.focus();
    		document.getElementById("lbl-password").innerHTML ="Password should have maximum 8 characters. Please enter.";
    		document.getElementById("lbl-password").style.color="Red";
        	isValidation= false;
        	return false;
    	}
    	isValidation=true;
    	document.getElementById("lbl-password").innerHTML ="";
        
    }
    if(document.frm.password1.value!=document.frm.password2.value){
    	document.frm.password2.focus();
    	document.getElementById("lbl-re-password").innerHTML ="Password doesn't match. Please retype again";
    	document.getElementById("lbl-re-password").style.color="Red";
    	isValidation = false;
    	return false;
    }else{
    	document.getElementById("lbl-re-password").innerHTML ="";
    	isValidation=true;
    }
    if(document.frm.securitycode.value==""){
    	document.frm.securitycode.focus();
    	document.getElementById("lbl-securitycode").innerHTML ="Security code is mandatory. Please enter.";
    	document.getElementById("lbl-securitycode").style.color="Red";
    	isValidation = false;
    	return false;
    }
   return  isValidation ;
    

}
</script>

</head>

<body>
  <section class="container">
    <div class="login">
      <h1>Skills Gap Analysis Tool - Self Registration</h1>
          <form name="frm" method="post" action="" onsubmit="return submitForm()">
        <p>UserID:<input  style="margin-left:35px; "type="text" name="username" value="" placeholder="UserID">
         e.g. JohnSmith, </p><p> <label  id="lbl-user-id" for="male"></label></p>
        <p>Password:<input  style="margin-left:22px; "type="password" name="password1" value="" placeholder="Password">
         e.g. 1secure2 </p><p><label  id="lbl-password" for="male"></label></p>
        <p>R-Password:<input style="margin-left:8px; " type="password" name="password2" value="" placeholder="Retype Password">
         </p><p><label  id="lbl-re-password" for="male"></label></p>
        <p>Access Code:<input  style="margin-left:5px; "type="text" name="securitycode" value="" placeholder="Access Code">
         </p><p><label  id="lbl-securitycode" for="male"></label></p>
        <p class="submit"><input type="submit" name="commit" value="Submit" ></p>
      </form>
     
    </div>

    <div class="login-help">
      <p>&nbsp;</p>
    </div>
</section>
</body>
</html>
