package com.prueba.abelda.usecases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.prueba.abelda.dto.CocheDto;
import com.prueba.abelda.mapper.CochesMapperImpl;
import com.prueba.abelda.mapper.PreciosMapperImpl;
import com.prueba.abelda.model.Coche;
import com.prueba.abelda.model.Marca;
import com.prueba.abelda.model.Precio;
import com.prueba.abelda.repository.CochesRepository;
import com.prueba.abelda.repository.MarcasRepository;
import com.prueba.abelda.service.impl.CochesServiceImpl;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class GetCocheUseCaseTest {

  @InjectMocks
  private CochesServiceImpl service;

  @Mock
  private CochesRepository cochesRepository;

  @Mock
  private MarcasRepository marcasRepository;

  @Spy
  private CochesMapperImpl cochesMapper;

  @Spy
  private PreciosMapperImpl preciosMapper;

  @Before
  public void setUp() {
    ReflectionTestUtils.setField(this.cochesMapper, "preciosMapper", preciosMapper);
  }

  @Test
  public void cochesService_getByIdAndDate_returnCocheOk() {
    String fecha = LocalDate.now().toString();
    // Arrange
    when(this.cochesRepository.findByIdAndDate(any(), any())).thenReturn(getCoche());

    // Act
    final CocheDto result = this.service.getByIdAndDate(1L, fecha);

    // Assert
    assertThat(result.getId(), is(equalTo(1L)));
    assertThat(result.getColor(), is(equalTo("azul")));
    verify(this.cochesRepository).findByIdAndDate(anyLong(), any());
  }

  @Test
  public void cochesService_getCochesByMarca_returnCochesOk() {
    String marca = "Ford";
    Marca marcaConCoche = getMarca();
    marcaConCoche.setCoches(Arrays.asList(getCoche().get()));
    // Arrange
    when(this.marcasRepository.findByMarca(any())).thenReturn(marcaConCoche);

    // Act
    final List<CocheDto> resultList = this.service.getCochesByMarcas(marca);

    // Assert
    assertThat(resultList.get(0).getColor(), is(equalTo("azul")));
    assertThat(resultList.get(0).getIdMarca(), is(equalTo(10L)));
    verify(this.marcasRepository).findByMarca(any());
  }

  private Marca getMarca() {
    Marca marca = new Marca();
    marca.setId(10L);
    marca.setMarca("Toyota");
    return marca;
  }

  private List<Precio> getPrecios() {
    Precio precio = new Precio();
    precio.setId(1L);
    precio.setPrecio(25000L);
    precio.setFechaFin(LocalDate.of(2030, 7, 10));
    precio.setFechaInicio(LocalDate.of(2010, 1, 10));
    return Arrays.asList(precio);
  }

  private Optional<Coche> getCoche() {
    Coche coche = new Coche();
    coche.setId(1L);
    coche.setColor("azul");
    coche.setMarca(getMarca());
    coche.setModelo("Corolla");
    coche.setPrecios(getPrecios());
    return Optional.of(coche);
  }
}
