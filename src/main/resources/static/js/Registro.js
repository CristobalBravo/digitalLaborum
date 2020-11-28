
function validacionPwd(){
	let pwd1 = document.getElementById("pwd1").value;
	let pwd2 = document.getElementById("pwd2").value;
	let error=document.getElementById("coincidencia");
	if(pwd1==pwd2){
		document.getElementById("formulario").submit();
	}else{
		error.innerHTML="<span style='color:red; text-align:center;'> Error las contraseñas no coinciden</span> <br>";
	}
	
}
