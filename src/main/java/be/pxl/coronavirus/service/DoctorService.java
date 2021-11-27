package be.pxl.coronavirus.service;

import be.pxl.coronavirus.API.Resources.DoctorResource;

import javax.transaction.Transactional;

public interface DoctorService {
    @Transactional
    Long createDoctor(DoctorResource doctorResource);
}
