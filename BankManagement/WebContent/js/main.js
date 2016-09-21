var renderData;
var renderHTML = {
		"error": "<div id='error-message'></div>",
		"insert": {
			"actionurl": "insert",
			"controls": [
			             {"type": "text", "placeholder": "Account Number", "id": "accountNumber"},
			             {"type": "text", "placeholder": "First Name", "id": "firstName"},
			             {"type": "text", "placeholder": "Last Name", "id": "lastName"},
			             {"type": "date", "placeholder": "Date of Birth", "id": "dateOfBirth"},
			             {"type": "text", "placeholder": "Phone Number", "id": "phoneNumber"},
			             {"type": "text", "placeholder": "Email", "id": "email"}
			            ],
			"buttonText": "Insert",
			"action": function() {
						var accountNumber = document.getElementById('accountNumber').value;
						var firstName = document.getElementById('firstName').value;
						var lastName = document.getElementById('lastName').value;
						var dateOfBirth = document.getElementById('dateOfBirth').value;
						var phoneNumber = document.getElementById('phoneNumber').value;
						var email = document.getElementById('email').value;
				
						// Do Javascript Regex Here
						AJAXRequest("insert",checkUser, "accountNumber="+accountNumber+"&firstName="+firstName+"&lastName="+lastName+"&dateOfBirth="+dateOfBirth+"&phoneNumber="+phoneNumber+"&email="+email);
					}			
		},
		"update": {
			"actionurl": "update",
			"buttonText": "Update",
			"action": function() {
				var accountNumber = document.getElementById('accountNumber').value;
				var firstName = document.getElementById('firstName').value;
				var lastName = document.getElementById('lastName').value;
				var dateOfBirth = document.getElementById('dateOfBirth').value;
				var phoneNumber = document.getElementById('phoneNumber').value;
				var email = document.getElementById('email').value;
		
				// Do Javascript Regex Here
				AJAXRequest("update",checkUser, "accountNumber="+accountNumber+"&firstName="+firstName+"&lastName="+lastName+"&dateOfBirth="+dateOfBirth+"&phoneNumber="+phoneNumber+"&email="+email);
			}			
		}
};

function checkUser(datas){
	if(datas.code === '1') {
		AJAXRequest("view", display);
		console.log(datas.message);
	} else {
		document.getElementById('error-message').innerText = datas.message;
		console.log(datas.message);
	}
}



function AJAXRequest(url, func, args) {
	if(args === undefined)
		args = "";
	var xhr = new XMLHttpRequest();
	xhr.open('POST', url, true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(args);
	xhr.onreadystatechange = function() {
		if(this.readyState == 4 && this.status == 200) {
			func(JSON.parse(this.responseText));
		}
	}
}

function myfunction() {
	AJAXRequest("view", display);
}

function display(datas) {
	renderDatas = datas;
	var elem = document.getElementById('content-holder');
	var table = "<table>\n";
	table += "<tr> <th>ID</th> <th>ACCOUNT NUMBER</th> <th>FIRST NAME</th> <th>LAST NAME</th> <th>DATE OF BIRTH</th> <th>PHONE NUMBER</th> <th>EMAIL</th> <th>EDIT</th> <th>DELETE</th> </tr>\n";
	for(var i = 0; i < datas.length; i++) {
		table += "<tr><td>"+datas[i].id + "</td><td>" +datas[i].accountNumber + "</td><td>" +datas[i].firstName + "</td><td>" +datas[i].lastName + "</td><td>" +datas[i].dateOfBirth + "</td><td>" +datas[i].phoneNumber + "</td><td>" +datas[i].email + '</td> <td><a class="edit-icon" data-target="'+i+'" href="#"></a></td><td><a class="delete-icon" data-target="'+datas[i].accountNumber+'" href="#"></a></td></tr>\n';
	}
	table += "</table>\n";
	elem.innerHTML = table;
	var edit = document.getElementsByClassName('edit-icon');
	for(var i = 0; i < edit.length; i++) {
		edit[i].addEventListener("click", function() {
			console.log("in event listener");
			var id = parseInt(this.getAttribute('data-target'));
			var idxv = ["accountNumber", "firstName", "lastName", "dateOfBirth", "phoneNumber", "email"];
			var repl = document.getElementById('content-holder');
			var tag = "<div id='form-display'>\n";
			var ctrls = renderHTML["insert"]["controls"];
			for(var i = 0; i < ctrls.length; i++) {
				if(i === 0)
					tag += '<input type="hidden" value="'+renderDatas[id][idxv[i]]+'" id="'+ctrls[i]["id"]+'" />\n';
				else
					tag += '<input type="'+ctrls[i]["type"]+'" value="'+renderDatas[id][idxv[i]]+'" id="'+ctrls[i]["id"]+'" />\n';
			}
			tag += '<input type="button" id="btn'+renderHTML["update"]["actionurl"]+'" value="'+renderHTML["update"]["buttonText"]+'" data-click="'+renderHTML["update"]["actionurl"]+'" />\n';
			tag += renderHTML["error"]+'\n';
			tag += '</div>\n';
			repl.innerHTML = tag;
			document.getElementById("btn"+renderHTML["update"]["actionurl"]).addEventListener("click", renderHTML["update"]["action"]);
		});
	}
	edit = document.getElementsByClassName("delete-icon");
	for(var i = 0; i < edit.length; i++) {
		edit[i].addEventListener("click", function() {
			AJAXRequest("delete",checkUser, "accountNumber="+this.getAttribute("data-target"));
		});
	}
}



window.addEventListener("load", myfunction);
window.addEventListener("load", function() {
	var tag = document.getElementById('insert-btn');
	tag.addEventListener("click", function(){
		var elem = document.getElementById('content-holder');
		var form = "<div id = 'form-display'>\n";
		var ctrls = renderHTML["insert"]["controls"];
		for(var i = 0; i < ctrls.length; i++) {
			form += '<input type="'+ctrls[i]["type"]+'" placeholder="'+ctrls[i]["placeholder"]+' " id="'+ctrls[i]["id"]+'" /><br>';
		}
		
		form += '<input type="button" id="btn'+renderHTML["insert"]["actionurl"]+'" value="'+renderHTML["insert"]["buttonText"]+'" data-click="'+renderHTML["insert"]["actionurl"]+'" /><br>';
		form += renderHTML["error"]+'\n';
		form += '</div>\n';
		elem.innerHTML = form;
		console.log(form);
		var button = document.getElementById("btn"+renderHTML["insert"]["actionurl"]);
		console.log(button);
		button.addEventListener("click", renderHTML["insert"]["action"]);
	});
});

