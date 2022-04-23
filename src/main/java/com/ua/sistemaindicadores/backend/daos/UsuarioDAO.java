/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ua.sistemaindicadores.backend.daos;

import com.ua.sistemaindicadores.backend.dtos.UsuarioDTO;
import com.ua.sistemaindicadores.backend.entities.Persona;
import com.ua.sistemaindicadores.backend.entities.Persona_;
import com.ua.sistemaindicadores.backend.entities.Rol;
import com.ua.sistemaindicadores.backend.entities.Rol_;
import com.ua.sistemaindicadores.backend.entities.Usuario;
import com.ua.sistemaindicadores.backend.entities.Usuario_;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Ricardo
 */
@Stateless
public class UsuarioDAO extends AbstractDAO<Usuario> {

    @Inject
    private EntityManagerProviderCredenciales entityManagerProviderCredenciales;

    @Override
    protected EntityManager getEntityManager() {
        return entityManagerProviderCredenciales.getEntityManager();
    }

    public UsuarioDAO() {
        super(Usuario.class);
    }

    public UsuarioDTO obtenerUsuarioDTO(Integer personaId) {
        List<UsuarioDTO> dtos = getEntityManager()
                .createNamedQuery("Usuario.obtenerDTO", UsuarioDTO.class)
                .setParameter("personaId", personaId)
                .getResultList();
        return dtos.isEmpty() ? null : dtos.get(0);
    }
    public List<UsuarioDTO> obtenerUsuarioCorreoDTO(String rolUsuario) {
        List<UsuarioDTO> dtos = getEntityManager()
                .createNamedQuery("Usuario.obtenerUsuarioCorreoDTO", UsuarioDTO.class)
                .setParameter("rolUsuario", rolUsuario)
                .getResultList();
        return dtos;
    }
    public int contar(Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Usuario> root = query.from(Usuario.class);

        Join<Usuario, Persona> joinPersona = root.join(Usuario_.PERSONA, JoinType.INNER);
        Join<Usuario, Rol> joinRoles = root.join(Usuario_.ROLES, JoinType.LEFT);

        if (filters != null && filters.containsKey(Rol_.NOMBRE)) {
            joinRoles = root.join(Usuario_.ROLES, JoinType.LEFT);
        }

        query.select(cb.count(root));

        Predicate p = cb.conjunction();
        if (filters != null && !filters.isEmpty()) {
            for (String key : filters.keySet()) {
                switch (key) {
                    case Persona_.NOMBRE_LEGAL:
                    case Persona_.NOMBRE_SOCIAL:
                    case Persona_.APELLIDO_MATERNO:
                    case Persona_.APELLIDO_PATERNO:
                    case Persona_.EMAIL:
                    case Persona_.EMAIL_INSTITUCIONAL:
                        p = cb.and(
                                p, cb.like(cb.lower(joinPersona.get(key)),
                                        cb.lower(cb.literal("%" + filters.get(key).toString() + "%")))
                        );
                        break;
                    case Persona_.RUT:
                        p = cb.and(
                                p, cb.equal(joinPersona.get(key),
                                        (Integer) filters.get(key))
                        );
                        break;
                    case Usuario_.CAMBIAR_CLAVE:
                    case Usuario_.HABILITADO:
                        p = cb.and(p, cb.equal(root.get(key), (Boolean) filters.get(key)));
                        break;
                    case Rol_.NOMBRE:
                        p = cb.and(
                                p,
                                cb.equal(joinRoles.get(key), (String) filters.get(key))
                        );
                        break;
                    default:
                        break;
                }
            }
        }
        query.where(p);

        Query q = getEntityManager().createQuery(query);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<UsuarioDTO> cargar(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<UsuarioDTO> cq = cb.createQuery(UsuarioDTO.class);
        
        //from
        Root<Usuario> rootUsuario = cq.from(Usuario.class);

        Join<Usuario, Persona> joinPersona = rootUsuario.join(Usuario_.PERSONA, JoinType.INNER);
        Join<Usuario, Rol> joinRoles = null;
        if (filters != null && filters.containsKey(Rol_.NOMBRE)) {
            joinRoles = rootUsuario.join(Usuario_.ROLES, JoinType.LEFT);
        }

        
        //select
        cq.select(
                cb.construct(
                        UsuarioDTO.class,
                        joinPersona.get(Persona_.RUT),
                        joinPersona.get(Persona_.DIGITO_VERIFICADOR),
                        joinPersona.get(Persona_.NOMBRE_LEGAL),
                        joinPersona.get(Persona_.NOMBRE_SOCIAL),
                        joinPersona.get(Persona_.APELLIDO_PATERNO),
                        joinPersona.get(Persona_.APELLIDO_MATERNO),
                        joinPersona.get(Persona_.GENERO_LEGAL),
                        joinPersona.get(Persona_.GENERO_SOCIAL),
                        joinPersona.get(Persona_.EMAIL),
                        joinPersona.get(Persona_.EMAIL_INSTITUCIONAL),
                        rootUsuario.get(Usuario_.PERSONA_ID),
                        rootUsuario.get(Usuario_.HABILITADO),
                        rootUsuario.get(Usuario_.FECHA_EXPIRACION),
                        rootUsuario.get(Usuario_.FECHA_ULTIMO_CAMBIO_CLAVE),
                        joinPersona.get(Persona_.FECHA_NACIMIENTO)
                )
        );

        //where
        Predicate p = cb.conjunction();
        if (filters != null && !filters.isEmpty()) {
            for (String key : filters.keySet()) {
                switch (key) {
                    case Persona_.NOMBRE_LEGAL:
                    case Persona_.NOMBRE_SOCIAL:
                    case Persona_.APELLIDO_MATERNO:
                    case Persona_.APELLIDO_PATERNO:
                    case Persona_.EMAIL:
                    case Persona_.EMAIL_INSTITUCIONAL:
                        p = cb.and(
                                p, cb.like(cb.lower(joinPersona.get(key)),
                                        cb.lower(cb.literal("%" + filters.get(key).toString() + "%")))
                        );
                        break;
                    case Persona_.RUT:
                        p = cb.and(
                                p, cb.equal(joinPersona.get(key),
                                        (Integer) filters.get(key))
                        );
                        break;
                    case Usuario_.CAMBIAR_CLAVE:
                    case Usuario_.HABILITADO:
                        p = cb.and(p, cb.equal(rootUsuario.get(key), (Boolean) filters.get(key)));
                        break;
                    case Rol_.NOMBRE:
                        p = cb.and(
                                p,
                                cb.equal(joinRoles.get(key), (String) filters.get(key))
                        );
                        break;
                    default:
                        break;
                }
            }
        }
        cq.where(p);
        
        //order by
        
        if (sortOrder != null && sortField != null) {
            switch (sortField) {
                case Persona_.NOMBRE_LEGAL:
                case Persona_.APELLIDO_PATERNO:
                case Persona_.APELLIDO_MATERNO:
                case Persona_.NOMBRE_SOCIAL:
                case Persona_.EMAIL:
                case Persona_.EMAIL_INSTITUCIONAL:
                case Persona_.RUT:
                    cq.orderBy(SORT_ASCENDING.equals(sortOrder)
                            ? cb.asc(joinPersona.get(sortField))
                            : cb.desc(joinPersona.get(sortField)));
                    break;
                default:
                    break;
            }
        }
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(pageSize);
        q.setFirstResult(first);
        return (List<UsuarioDTO>) q.getResultList();
    }
    
    public Usuario obtenerUsuarioPersonaId(Integer personaId)
    {
        return getEntityManager()
                .createNamedQuery("Usuario.obtenerPersonaId",Usuario.class)
                .setParameter("personaId", personaId)
                .getSingleResult();
    }
    public Usuario obtenerUsuarioRut(Integer rut)
    {
        List<Usuario> usuarios = getEntityManager().createNamedQuery("Usuario.obtenerPersonaRut",Usuario.class).setParameter("rut", rut).getResultList();
        return usuarios.isEmpty()? null : usuarios.get(0);
    }
}
