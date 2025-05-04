package com.cibertec.runner.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cibertec.runner.dto.request.FiltroProductoDTO;
import com.cibertec.runner.dto.request.ProductoDTO;
import com.cibertec.runner.dto.response.SuccessResponse;
import com.cibertec.runner.model.Producto;
import com.cibertec.runner.repository.IProductoRepository;
import com.cibertec.runner.service.ProductoService;

@Service

public class ProductoServiceImp implements ProductoService {

	@Autowired
	private IProductoRepository prorepo;

    @Override
    public ResponseEntity<SuccessResponse<List<Producto>>> findAllProductos() {
        List<Producto> productos = prorepo.findAll();

        if (!productos.isEmpty()) {
            SuccessResponse<List<Producto>> success = SuccessResponse.<List<Producto>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(productos)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("No existen registros");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Producto>> findByIdProducto(Integer id) {
        Optional<Producto> producto = prorepo.findById(id);

        if (producto.isPresent()) {
            SuccessResponse<Producto> success = SuccessResponse.<Producto>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(producto.get())
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new RuntimeException("No se encuentra un registro para el ID: " + id);
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<Producto>> saveProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto();
        producto.setStock(productoDTO.getStock());
        producto.setIdClr(productoDTO.getIdClr());
        producto.setIdTll(productoDTO.getIdTll());
        producto.setIdMdl(productoDTO.getIdMdl());

        Producto productoGuardado = prorepo.save(producto);

        SuccessResponse<Producto> success = SuccessResponse.<Producto>builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED.value())
                .success(HttpStatus.CREATED.getReasonPhrase())
                .response(productoGuardado)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(success);
    }

    @Override
    public ResponseEntity<SuccessResponse<Producto>> updateProducto(Producto producto, Integer id) {
        Optional<Producto> productoExistente = prorepo.findById(id);

        if (productoExistente.isPresent()) {
            Producto productoActualizado = productoExistente.get();
            productoActualizado.setStock(producto.getStock());
            productoActualizado.setIdClr(producto.getIdClr());
            productoActualizado.setIdTll(producto.getIdTll());
            productoActualizado.setIdMdl(producto.getIdMdl());

            Producto productoGuardado = prorepo.save(productoActualizado);

            SuccessResponse<Producto> success = SuccessResponse.<Producto>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(productoGuardado)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(success);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<String>> deleteByIdProducto(Integer id) {
        Optional<Producto> productoExiste = prorepo.findById(id);

        if (productoExiste.isPresent()) {
            prorepo.delete(productoExiste.get());

            SuccessResponse<String> success = SuccessResponse.<String>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.NO_CONTENT.value())
                    .success(HttpStatus.NO_CONTENT.getReasonPhrase())
                    .response("Producto eliminado correctamente")
                    .build();

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(success);
        } else {
            throw new RuntimeException("No se realizó la eliminación, Producto no encontrado");
        }
    }

    @Override
    public ResponseEntity<SuccessResponse<List<Producto>>> findByIdMdl(Integer idMdl) {
        List<Producto> productos = prorepo.findByIdMdl(idMdl);

        if (!productos.isEmpty()) {
            SuccessResponse<List<Producto>> success = SuccessResponse.<List<Producto>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(productos)
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new RuntimeException("No se encontraron productos para el modelo con ID: " + idMdl);
        }
    }
    
    public ResponseEntity<SuccessResponse<List<Producto>>> findByAttributes(FiltroProductoDTO id) {
        List<Producto> productos = prorepo.findAll();
        
        List<Producto> pr =  productos.stream()
                .filter(p ->  
                (id.getIdClr() == 0 || id.getIdClr().equals(p.getIdClr()))
                && (id.getIdTll() == 0 || id.getIdTll().equals(p.getIdTll()))
                && (id.getIdCtg() == 0 || id.getIdCtg().equals(p.getModelo().getIdCtg()))
                && (id.getIdMrc() == 0 || id.getIdMrc().equals(p.getModelo().getIdMrc()))
                && (id.getIdPrn() == 0 || id.getIdPrn().equals(p.getModelo().getIdPrn()))
                && (id.getIdMtl() == 0 || id.getIdMtl().equals(p.getModelo().getIdMtl()))
                )
                .collect(Collectors.toList());

        if (!productos.isEmpty()) {
            SuccessResponse<List<Producto>> success = SuccessResponse.<List<Producto>>builder()
                    .timestamp(LocalDateTime.now())
                    .status(HttpStatus.OK.value())
                    .success(HttpStatus.OK.getReasonPhrase())
                    .response(pr)
                    .build();

            return ResponseEntity.ok(success);
        } else {
            throw new RuntimeException("No se encontraron productos para el modelo con ID: " + id.toString());
        }
    }


}
