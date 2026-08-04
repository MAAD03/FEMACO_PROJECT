package com.femaco.main.Service.Seguridad;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.femaco.main.Entity.Seguridad.Menu;
import com.femaco.main.Repository.Seguridad.MenuRepository;

@Service
public class MenuService {
    
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<Menu> buscarTodos() {
        return menuRepository.findAll();
    }

    @Transactional
    public Menu crear(Menu menu) {
        LocalDateTime ahora = LocalDateTime.now();
        menu.setIdMenu(null);
        menu.setFechaCreacion(ahora);
        menu.setFechaModif(ahora);
        menu.setUsuarioModif(menu.getUsuarioCreacion());
        return menuRepository.save(menu);
    }

    @Transactional
    public Optional<Menu> actualizar(Long idMenu, Menu datosNuevos) {
        return menuRepository.findById(idMenu).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setOrdenMenu(datosNuevos.getOrdenMenu());

            existente.setUsuarioModif(datosNuevos.getUsuarioModif());
            existente.setFechaModif(LocalDateTime.now());
            return menuRepository.save(existente);
        });
    }

    @Transactional
    public boolean eliminar(Long idMenu) {
        if (!menuRepository.existsById(idMenu)) {
            return false;
        }
        menuRepository.deleteById(idMenu);
        return true;
    }

}
