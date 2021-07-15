package com.newsaleapi.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.newsaleapi.Entity.BarcodeEntity;
import com.newsaleapi.Entity.DeliverySlipEntity;
import com.newsaleapi.mapper.NewSaleMapper;
import com.newsaleapi.repository.BarcodeRepository;
import com.newsaleapi.repository.DeliverySlipRepository;
import com.newsaleapi.service.NewSaleService;
import com.newsaleapi.vo.BarcodeVo;
import com.newsaleapi.vo.CustomerDetails;
import com.newsaleapi.vo.DeliverySlipVo;

@Service
@Configuration
public class NewSaleServiceImpl implements NewSaleService {

	@Autowired
	private RestTemplate template;

	@Autowired
	private NewSaleMapper newSaleMapper;

	@Autowired
	private BarcodeRepository barcodeRepository;

	@Autowired
	private DeliverySlipRepository dsRepo;

	@Value("${savecustomer.url}")
	private String url;

	@Override
	public ResponseEntity<?> saveNewSaleRequest(CustomerDetails vo) {

		Object result = template.postForObject(url, vo, String.class);

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> saveBarcode(BarcodeVo vo) {

		BarcodeEntity barcodeDetails = barcodeRepository.findByBarcode(vo.getBarcode());
		if (barcodeDetails == null) {

			BarcodeEntity barcode = newSaleMapper.convertBarcodeVoToEntity(vo);
			barcodeRepository.save(barcode);

			return new ResponseEntity<>("Barcode details saved successfully..", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Barcode with " + vo.getBarcode() + " is already exists",
					HttpStatus.BAD_GATEWAY);
		}
	}

	@Override
	public ResponseEntity<?> getBarcodeDetails(String barCode) {

		BarcodeEntity barcodeDetails = barcodeRepository.findByBarcode(barCode);

		if (barcodeDetails == null) {
			return new ResponseEntity<>("Barcode with number " + barCode + " is not exists", HttpStatus.BAD_GATEWAY);
		} else {
			BarcodeVo vo = newSaleMapper.convertBarcodeEntityToVo(barcodeDetails);
			return new ResponseEntity<>(vo, HttpStatus.OK);
		}
	}
	/*
	 * @Override public ResponseEntity<?> saveDeliverySlip(DeliverySlipVo vo) {
	 * 
	 * 
	 * DeliverySlipEntity dsEntity = newSaleMapper.convertDsVoToEntity(vo);
	 * 
	 * dsRepo.save(dsEntity);
	 * 
	 * return new ResponseEntity<>("Deliveryslip details saved successfully..",
	 * HttpStatus.OK); }
	 */

	@Override
	public ResponseEntity<?> saveDeliverySlip(DeliverySlipVo vo) {

		DeliverySlipEntity dsEntity = newSaleMapper.convertDsVoToEntity(vo);
		dsRepo.save(dsEntity);

		return new ResponseEntity<>("Deliveryslip details saved successfully..", HttpStatus.OK);
	}

}
