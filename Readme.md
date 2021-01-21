This is a RESTfull application for Vet clinic.

Application consumes GET, POST and DELETE requests.

Requests could be sent by JSON or XML file.

Endpoins are set out on /vet-clinic/appointment

GET method allows user to get all appointments for selected doctor and date.

POST method allows user to create new appointment.
Structure of the JSON file for such request:

{
"customer": {
"digitID": "",
"digitPIN": "",
"email": ""
},
"localDateTime": "",
"doctor": {
"doctorId": ""
}
}

DELETE method allows user to delete appointment based on its ID.


