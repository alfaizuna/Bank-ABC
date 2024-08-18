package com.alfaizuna.bankabc.service.impl;

import com.alfaizuna.bankabc.dto.request.RegisterCustomerRequestDTO;
import com.alfaizuna.bankabc.dto.request.TransferSaldoRequestDTO;
import com.alfaizuna.bankabc.dto.response.CustomerSaldoResponseDTO;
import com.alfaizuna.bankabc.entity.Customer;
import com.alfaizuna.bankabc.repository.CustomerRepository;
import com.alfaizuna.bankabc.service.CustomerService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findCustomerByNIK(Long nik) {
        return customerRepository.findByNik(nik).orElse(null);
    }

    @Override
    public Customer findCustomerByAccountNumber(String accountNumber) throws Exception {
        Customer customer = customerRepository.findByAccountNumber(accountNumber).orElse(null);
        if (Objects.isNull(customer)) {
            throw new Exception("customer not found with this account number: " + accountNumber);
        }
        return customer;
    }

    @Override
    public Customer registerCustomer(RegisterCustomerRequestDTO request) throws Exception {
        // add new customer
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setNik(request.getNik());
        customer.setSaldo(0L);
        Customer save = customerRepository.save(customer);
        customer.setAccountNumber(generateAccountNumber(save.getId()));
        return customerRepository.save(customer);
    }

    @Override
    public CustomerSaldoResponseDTO checkSaldo(Long customerId) throws Exception {
        Customer customer = this.checkCustomerExistByCustomerId(customerId);

        // get info customer
        CustomerSaldoResponseDTO response = new CustomerSaldoResponseDTO();
        response.setName(customer.getName());
        response.setAmountSaldo(customer.getSaldo());
        return response;
    }

    @Override
    public CustomerSaldoResponseDTO addSaldo(Long customerId, Long amountSaldo) throws Exception {
        Customer customer = this.checkCustomerExistByCustomerId(customerId);

        // update saldo customer
        customer.setSaldo(this.calculateIncreaseSaldo(amountSaldo, customer));
        customerRepository.save(customer);
        return this.checkSaldo(customerId);
    }

    @Override
    public List<CustomerSaldoResponseDTO> transferSaldo(TransferSaldoRequestDTO requestDTO) throws Exception {
        Customer fromAccount = this.findCustomerByAccountNumber(requestDTO.getFromAccount());
        Customer toAccount = this.findCustomerByAccountNumber(requestDTO.getToAccount());

        fromAccount.setSaldo(this.calculateDecreaseSaldo(requestDTO.getAmount(), fromAccount));
        toAccount.setSaldo(this.calculateIncreaseSaldo(requestDTO.getAmount(), toAccount));

        customerRepository.save(fromAccount);
        customerRepository.save(toAccount);

        CustomerSaldoResponseDTO fromAccountDTO = new CustomerSaldoResponseDTO(fromAccount.getAccountNumber(), fromAccount.getSaldo());
        CustomerSaldoResponseDTO toAccountDTO = new CustomerSaldoResponseDTO(toAccount.getAccountNumber(), toAccount.getSaldo());
        return List.of(fromAccountDTO, toAccountDTO);
    }

    @Override
    public CustomerSaldoResponseDTO tarikSaldo(Long customerId, Long amountSaldo) throws Exception {
        Customer customer = this.findCustomerById(customerId);
        customer.setSaldo(this.calculateDecreaseSaldo(amountSaldo, customer));
        customerRepository.save(customer);
        return this.checkSaldo(customerId);
    }

    @Override
    public String generateAccountNumber(Long customerId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        return date + String.format("%04d", customerId);
    }

    private Customer checkCustomerExistByCustomerId(Long customerId) throws Exception {
        Customer customer = this.findCustomerById(customerId);
        if (Objects.isNull(customer)) {
            throw new Exception("customer not found with this customerId: " + customerId);
        }
        return customer;
    }

    private long calculateIncreaseSaldo(Long amountSaldo, Customer customer) {
        return customer.getSaldo() + amountSaldo;
    }

    private long calculateDecreaseSaldo(Long amountSaldo, Customer customer) {
        return customer.getSaldo() - amountSaldo;
    }
}
