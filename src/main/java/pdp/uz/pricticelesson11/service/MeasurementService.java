package pdp.uz.pricticelesson11.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.pricticelesson11.entity.Measurement;
import pdp.uz.pricticelesson11.entity.Warehouse;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.repository.MeasurementRepository;
import pdp.uz.pricticelesson11.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public List<Measurement> getAll() {
        List<Measurement> all = measurementRepository.findAll();
        return all;
    }

    public Measurement getById(Integer id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()) {
            Measurement measurement = byId.get();
            return measurement;
        }
        return new Measurement();

    }

    public ApiResponse addMeasurement(Measurement measurement) {
        Measurement measurement1 = new Measurement();

        if (measurementRepository.existsByName(measurement.getName())) {
            return new ApiResponse("Bunday nameli Measurement mavjud", false);
        }
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new ApiResponse("saved", true);

    }

    public ApiResponse edit(Integer id, Measurement measurement) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        boolean exists = measurementRepository.existsByName(measurement.getName());
        if (!byId.isPresent()){
            return new ApiResponse("Not found Id", false);
        }
        if (exists){
            return new ApiResponse("Bunday nameli Measurement mavjud", false);
        }
        Measurement measurement1 = byId.get();
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()){
            measurementRepository.deleteById(id);
            return new ApiResponse("Measurement deleted", true);
        }
        return new ApiResponse("not found Id", false);
    }


}
