package com.three.recipingbackofficeservicebe.event_management.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "EventAdminService")
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EventAdminService {

}
