// all validation of the fields will go here 

// this function will be called when user clicks on submit button and 
// this function will call all other functions to validate and if any of the validation 
// fails then it return false, if all fields are validated returns true 
function fnValidateForm(){
		
	if(!fnValidateName()) {
		return false;
	}
	
	if(!fnValidatePassword()) {
		return false;
	}

	if(!fnValildateSex()) {
		return false;
	}

	if(!fnValidateQualification()) {
		return false;
	}

	if(!fnValidateAddress()) { 
		return false;
	}
		
	if(!fnValidateInterest()) {
		return false;
	}


	if(!fnValidateCountry()) {
		return false;
	}

	return true;
}


function fnValidateName(){
	
	fname=document.registerForm.userName.value;

	if(fname.length<6){
		alert("Please enter name more than 6 chars");
		//document.regForm.userName.focus();
		return false;
	}

	if(fname.charAt(0)==" "){
		document.registerForm.userName.value="";
		alert("Sorry name cannot start with leading spaces");
		return false;
	}
		
	spl="1234567890`~!@#$%^&*()_+=-\";:/.,<>?";

	for(var i=0; i<spl.length; i++){
		if(fname.indexOf(spl.charAt(i))>=0){
		document.registerForm.userName.value="";
		alert("Sorry Name cannot have special chars or numbers ");
			return false;
		}
	}
	return true;
}

function fnValidatePassword(){
	passwd = document.registerForm.userPwd.value; 
	if(passwd.length<6){
		alert("Sorry password cannot be less than 6 chars");
		return false;
	}

	repasswd = document.registerForm.cPwd.value; 

	if(passwd.length==repasswd.length){
		for(var i=0; i<passwd.length; i++){
			if(passwd.charAt(i)!=repasswd.charAt(i)){
				alert("Sorry password mismatch");	
				return false;
			}
		}
	}else{
		alert("Password length mis match");
		return false;
	}

	return true;
}

function fnValildateSex(){
	s = document.registerForm.sex;
	
	for(var i=0; i<s.length; i++){
		if(s[i].checked)
			return true;
	}
	alert("Please select your sex");
	return false;
}

function fnValidateAddress(){
	address = document.registerForm.addrs.value; 

	if(address.charAt(0)==" "){
		alert("Sorry address Cannot start with a space");
		return false;
	}

	if(address.length<8){
		alert("Address length should be more than 8 chars");
		return false;
	}

	return true;
}

function fnValidateInterest(){
	if(document.registerForm.cb1.checked || document.registerForm.cb2.checked || 
		document.registerForm.cb3.checked || document.registerForm.cb4.checked ){
		return true;
	}

	alert("Please select your area of interest");
	return false;
}



function fnValidateCountry(){
	country = document.registerForm.country;

	if(country.selectedIndex<1){
		alert("please select the country from the list");
		return false;
	}

	return true;
}


function fnValidateQualification(){
	quali = document.registerForm.qual;

	if(quali.selectedIndex<1){
		alert("Sorry please select your Qualification ");
		return false;
	}

	return true;
}


// for enabling 
function fnEnableReg(){
	if(document.registerForm.terms[0].checked)
		document.registerForm.regBtn.disabled=false;
}

// for disabling 
function fnDisableReg(){
	if(document.registerForm.terms[1].checked)
		document.registerForm.regBtn.disabled=true;
}







