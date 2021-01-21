package pl.kuziow.vetclinic.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kuziow.vetclinic.dto.AppointmentDTO;
import pl.kuziow.vetclinic.request.AppointmentRequestModel;
import pl.kuziow.vetclinic.response.AppointmentREST;
import pl.kuziow.vetclinic.response.OperationStatusModel;
import pl.kuziow.vetclinic.response.RequestOperationName;
import pl.kuziow.vetclinic.response.RequestOperationStatus;
import pl.kuziow.vetclinic.service.AppointmentService;
import pl.kuziow.vetclinic.service.ErrorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointment") //http://localhost:8080/vet-clinic/appointment
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ErrorService errorService;

    @GetMapping(path = "/{doctorId}/{date}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<AppointmentREST> getAppointmentsForDoctorForDate(@PathVariable String doctorId, @PathVariable String date) {
        List<AppointmentREST> returnValue = new ArrayList<>();
        List<AppointmentDTO> appointmentsDTO = appointmentService.getAppointmentsByIdAndDate(doctorId, date);
        if (appointmentsDTO != null && !appointmentsDTO.isEmpty()) {
            java.lang.reflect.Type listType = new TypeToken<List<AppointmentREST>>() {
            }.getType();
            returnValue = new ModelMapper().map(appointmentsDTO, listType);
        }
        return returnValue;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public AppointmentREST makeAppointment(@RequestBody AppointmentRequestModel appointmentDetails) throws Exception{

        errorService.checkRequiredFields(appointmentDetails);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        AppointmentDTO appointmentDTO = modelMapper.map(appointmentDetails, AppointmentDTO.class);

        AppointmentDTO createdAppintment = appointmentService.createAppointment(appointmentDTO);

        AppointmentREST returnValue = modelMapper.map(createdAppintment, AppointmentREST.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public OperationStatusModel deleteAppoitment(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        appointmentService.delete(id);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }
}
