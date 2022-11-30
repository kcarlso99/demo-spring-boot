package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.files.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

}
