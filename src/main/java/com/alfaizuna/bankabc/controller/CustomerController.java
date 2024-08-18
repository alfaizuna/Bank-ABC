package com.alfaizuna.bankabc.controller;

import com.alfaizuna.bankabc.dto.request.RegisterCustomerRequestDTO;
import com.alfaizuna.bankabc.dto.request.TransferSaldoRequestDTO;
import com.alfaizuna.bankabc.dto.response.CustomerSaldoResponseDTO;
import com.alfaizuna.bankabc.entity.Customer;
import com.alfaizuna.bankabc.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> registerCustomer(@RequestBody RegisterCustomerRequestDTO request) throws Exception {
        try {
            Customer customerByNIK = customerService.findCustomerByNIK(request.getNik());
            if (customerByNIK != null) {
                return ResponseEntity.status(400).eTag("Customer already exists").build();
            }
            Customer customer = customerService.registerCustomer(request);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            throw new Exception("Something went wrong!", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerSaldoResponseDTO> checkSaldo(@PathVariable Long id) throws Exception {
        try {
            CustomerSaldoResponseDTO response = customerService.checkSaldo(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception("Something went wrong!", e);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerSaldoResponseDTO> addSaldo(
            @PathVariable Long id,
            @RequestParam Long amountSaldo
    ) throws Exception {
        try {
            CustomerSaldoResponseDTO response = customerService.addSaldo(id, amountSaldo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception("Something went wrong!", e);
        }
    }

    @PostMapping("/transfer-saldo")
    public ResponseEntity<List<CustomerSaldoResponseDTO>> transferSaldo(@RequestBody TransferSaldoRequestDTO requestDTO) throws Exception {
        try {
            CustomerSaldoResponseDTO customerSaldoResponseDTO = customerService.checkSaldo(requestDTO.getFromCustomerId());
            if (customerSaldoResponseDTO.getAmountSaldo() < requestDTO.getAmount()) {
                return ResponseEntity.status(400).eTag("Saldo is not enough!").build();
            }
            List<CustomerSaldoResponseDTO> response = customerService.transferSaldo(requestDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception("Something went wrong!", e);
        }
    }

    @PostMapping("/tarik-saldo/{id}")
    public ResponseEntity<CustomerSaldoResponseDTO> transferSaldo(
            @PathVariable Long id,
            @RequestParam Long amountSaldo) throws Exception {
        try {
            CustomerSaldoResponseDTO customerSaldoResponseDTO = customerService.checkSaldo(id);
            if (customerSaldoResponseDTO.getAmountSaldo() < amountSaldo) {
                return ResponseEntity.status(400).eTag("Saldo is not enough!").build();
            }
            CustomerSaldoResponseDTO response = customerService.tarikSaldo(id, amountSaldo);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new Exception("Something went wrong!", e);
        }
    }
}
