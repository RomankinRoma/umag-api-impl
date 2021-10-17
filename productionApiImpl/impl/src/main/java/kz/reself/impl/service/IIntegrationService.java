package kz.reself.impl.service;

import kz.reself.impl.model.integration.Debit;
import kz.reself.impl.model.integration.Decomission;
import kz.reself.impl.model.integration.DecomissionDTO;
import kz.reself.impl.model.integration.ProductBarcodeInf;
import kz.reself.impl.model.integration.ProductDTO;
import kz.reself.impl.model.integration.UmagUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IIntegrationService {

  UmagUser login();

  List<ProductDTO> getAllProducts(Map<String, String> params);

  ProductBarcodeInf getProductById(String barcode, String storeId);

  Object getDecomisionDebitByStoreId(Map<String, String> allParams);

  DecomissionDTO getDecomisionByStoreId(String id, String storeId);

  Object getDebitByStoreId(String id, String storeId);

  Object createDebit(Debit debit, String storeId);

  Object createDecommission(Decomission decomision, String storeId);

  ResponseEntity<HttpStatus> deleteDebit(Long id, String storeId);

  ResponseEntity<HttpStatus> deleteDecomission(Long id, String storeId);
}
