package com.ducanh.cinematicketing.repository;

import com.ducanh.cinematicketing.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
