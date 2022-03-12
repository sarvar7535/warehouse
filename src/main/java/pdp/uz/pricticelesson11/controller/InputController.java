package pdp.uz.pricticelesson11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.pricticelesson11.entity.Input;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.payload.InputDTO;
import pdp.uz.pricticelesson11.service.InputService;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @GetMapping("/getAll")
    public List<Input> getAll() {
        return inputService.getAll();
    }

    @GetMapping("/ById/{id}")
    public Input getById(@PathVariable Integer id) {
        return inputService.ByIdGet(id);
    }

    @PostMapping("/added")
    public ApiResponse add(@RequestBody InputDTO inputDTO) {
        return inputService.add(inputDTO);
    }

    @PutMapping("/edit/{id}")
    public ApiResponse edited(@PathVariable Integer id, @RequestBody InputDTO inputDTO) {
        return inputService.edit(id, inputDTO);
    }

    @DeleteMapping("/deleted/{id}")
    public ApiResponse delete(@PathVariable Integer id) {
        return inputService.delete(id);
    }
}
