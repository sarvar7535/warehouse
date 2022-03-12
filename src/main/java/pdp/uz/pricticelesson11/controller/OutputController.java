package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Input;
import pdp.uz.pricticelesson11.entity.Output;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputDTO;
import pdp.uz.pricticelesson11.payload.OutputDTO;
import pdp.uz.pricticelesson11.service.InputService;
import pdp.uz.pricticelesson11.service.OutputService;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @GetMapping("/getAll")
    public List<Output> getAll() {
        return outputService.getAll();
    }

    @GetMapping("/ById/{id}")
    public Output getById(@PathVariable Integer id) {
        return outputService.ByIdGet(id);
    }

    @PostMapping("/added")
    public ApiResponse add(@RequestBody OutputDTO outputDTO) {
        return outputService.add(outputDTO);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edited(@PathVariable Integer id, @RequestBody OutputDTO outputDTO) {
        return outputService.edit(id, outputDTO);
    }

    @DeleteMapping("/deleted/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return outputService.delete(id);
    }
}
