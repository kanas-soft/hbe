package com.kanas.hbe.user.service.impl;

import com.kanas.hbe.user.domain.entity.Contact;
import com.kanas.hbe.user.repository.ContactRepository;
import com.kanas.hbe.user.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public JpaRepository<Contact, Long> getJpaRepository() {
        return contactRepository;
    }

    @Override
    public Logger getLogger() {
        return log;
    }

    @Override
    public Class<Contact> getType() {
        return Contact.class;
    }
}
