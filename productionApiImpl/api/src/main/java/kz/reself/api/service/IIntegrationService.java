package kz.reself.api.service;

import kz.reself.api.model.Debit;
import kz.reself.api.model.Decomission;
import kz.reself.api.model.DecomissionDTO;
import kz.reself.api.model.ProductBarcodeInf;
import kz.reself.api.model.ProductDTO;
import kz.reself.api.model.UmagUser;
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
