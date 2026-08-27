package com.femaco.main.Repository.Seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.femaco.main.Config.MenuUsuarioProjection;
import com.femaco.main.Entity.Seguridad.Menu;

public interface ConjuntoMenuRepository extends JpaRepository<Menu, Integer> {
    @Query(value = """
        SELECT 
            m.IdModulo AS idModulo, 
            m.Nombre AS nombreModulo, 
            m.OrdenMenu AS ordenModulo,
            me.IdMenu AS idMenu, 
            me.Nombre AS nombreMenu, 
            me.OrdenMenu AS ordenMenu,
            o.IdOpcion AS idOpcion, 
            o.Nombre AS nombreOpcion, 
            o.OrdenMenu AS ordenOpcion, 
            o.Pagina AS pagina,
            ro.Alta AS alta, 
            ro.Baja AS baja, 
            ro.Cambio AS cambio
        FROM usuario u
        INNER JOIN rol_opcion ro ON ro.IdRol = u.IdRol
        INNER JOIN opcion o ON o.IdOpcion = ro.IdOpcion
        INNER JOIN menu me ON me.IdMenu = o.IdMenu
        INNER JOIN modulo m ON m.IdModulo = me.IdModulo
        WHERE u.IdUsuario = :idUsuario
        ORDER BY m.OrdenMenu, me.OrdenMenu, o.OrdenMenu
        """, nativeQuery = true)
    List<MenuUsuarioProjection> findMenuByUsuario(@Param("idUsuario") Long idUsuario);
}
