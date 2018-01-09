package com.ace.training.service;

import javax.xml.bind.JAXBException;

import com.ace.training.exceptions.DaoException;

public interface UploadToDbService {

	public abstract void parseXml(String fileName) throws JAXBException, DaoException;

}