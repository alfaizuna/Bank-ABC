package com.alfaizuna.bankabc.service;

import com.alfaizuna.bankabc.dto.request.RegisterCustomerRequestDTO;
import com.alfaizuna.bankabc.dto.request.TransferSaldoRequestDTO;
import com.alfaizuna.bankabc.dto.response.CustomerSaldoResponseDTO;
import com.alfaizuna.bankabc.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer findCustomerById(Long id);

    Customer findCustomerByNIK(Long nik);

    Customer findCustomerByAccountNumber(String accountNumber) throws Exception;

    Customer registerCustomer(RegisterCustomerRequestDTO request) throws Exception;

    CustomerSaldoResponseDTO checkSaldo(Long customerId) throws Exception;

    CustomerSaldoResponseDTO addSaldo(Long customerId, Long amountSaldo) throws Exception;

    List<CustomerSaldoResponseDTO> transferSaldo(TransferSaldoRequestDTO requestDTO) throws Exception;

    CustomerSaldoResponseDTO tarikSaldo(Long customerId, Long amountSaldo) throws Exception;

    String generateAccountNumber(Long customerId);
}
