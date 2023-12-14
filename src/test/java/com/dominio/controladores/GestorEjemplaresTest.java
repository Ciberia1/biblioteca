package com.dominio.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dominio.entidades.Ejemplar;
import com.dominio.entidades.Obra;
import com.persistencia.EjemplarDAO;
import com.persistencia.ObraDAO;

class GestorEjemplaresTest {

    @InjectMocks
    private GestorEjemplares gestorEjemplares;

    @Mock
    private EjemplarDAO ejemplarDAO;

    @Mock
    private ObraDAO obraDAO;

    @BeforeEach
    protected void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    
	@AfterEach
	protected void tearDown() throws Exception {
	}


    @Test
    public void testAltaEjemplar() throws InterruptedException {
        Obra obra = new Obra();
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);
        when(obraDAO.findById(anyLong())).thenReturn(java.util.Optional.of(obra));

        String result = gestorEjemplares.altaEjemplar(1L, 1, redirectAttributes);

        verify(ejemplarDAO, times(1)).save(any(Ejemplar.class));
        assertEquals("redirect:/gestionEjemplar", result);
    }

    @Test
    public void testBajaEjemplar() {
        Map<String, List<Long>> data = new HashMap<>();
        data.put("id", Arrays.asList(1L, 2L));

        String result = gestorEjemplares.bajaEjemplar(data);

        verify(ejemplarDAO, times(2)).deleteById(anyLong());
        assertEquals("redirect:/inicio", result);
    }

}
