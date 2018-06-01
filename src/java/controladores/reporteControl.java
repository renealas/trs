package controladores;

import entidades.Culto;
import entidades.Departamentos;
import entidades.Iglesia;
import entidades.Municipio;
import entidades.Pais;
import entidades.Zona;
import java.io.IOException;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.CultoFacade;
import modelo.DepartamentosFacade;
import modelo.IglesiaFacade;
import modelo.MunicipioFacade;
import modelo.PaisFacade;
import modelo.ZonaFacade;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean(name = "reporteControl")
@ViewScoped
public class reporteControl implements Serializable {

    @EJB
    private ZonaFacade zonaFacade;

    @EJB
    private PaisFacade paisFacade;

    @EJB
    private DepartamentosFacade departamentoFacade;

    @EJB
    private MunicipioFacade municipioFacade;

    @EJB
    private IglesiaFacade iglesiaFacade;

    @EJB
    private CultoFacade cultoFacade;

    private int tipoReporte;
    private int tipoSeccion;
    private Date fechaInicio;
    private Date fechaFin;
    private Zona zonaSelected;
    private List<Zona> lstZona;
    private Pais paisSelected;
    private List<Pais> lstPais;
    private Departamentos departamentoSelected;
    private List<Departamentos> lstDepartamento;
    private Municipio municipioSelected;
    private List<Municipio> lstMunicipio;
    private Iglesia iglesiaSelected;
    private List<Iglesia> lstIglesia;
    private Culto cultoSelected;
    private List<Culto> lstCulto;
    Connection conn = null;
    String DATASOURCE_CONTEXT = "java:app/jdbc/TRS";
    JasperPrint jasperPrint;
    HttpServletResponse httpServletResponse = null;

    @PostConstruct
    public void init() {
        zonaSelected = new Zona();
        paisSelected = new Pais();
        departamentoSelected = new Departamentos();
        municipioSelected = new Municipio();
        iglesiaSelected = new Iglesia();
        cultoSelected = new Culto();
        listarZonas();
        listarPaises();
        listarDepartamentos();
        listarMunicipios();
        listarIglesias();
        listarCultos();
        try {
            Context initialContext = new InitialContext();
            DataSource datasource = (DataSource) initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                conn = datasource.getConnection();
            } else {
                System.out.println("Error al buscar el datasource.");
            }
        } catch (NamingException | SQLException ex) {
            System.out.println("No se puede obtener la conexión: " + ex.getMessage());
        }
    }

    private void listarZonas() {
        lstZona = zonaFacade.findAll();
    }

    private void listarPaises() {
        lstPais = paisFacade.findAll();
    }

    public void listarDepartamentos() {
        if (paisSelected != null) {
            lstDepartamento = departamentoFacade.listarDepartamentosPorPais(paisSelected);
        } else {
            lstDepartamento = departamentoFacade.findAll();
        }
    }

    public void listarMunicipios() {
        if (departamentoSelected != null) {
            lstMunicipio = municipioFacade.listarMunicipiosPorDepartamento(departamentoSelected);
        } else {
            lstDepartamento = departamentoFacade.findAll();
        }
    }

    public void listarIglesias() {
        if (municipioSelected != null) {
            lstIglesia = iglesiaFacade.listarIglesiasPorMunicipio(municipioSelected);
        } else {
            lstIglesia = iglesiaFacade.findAll();
        }
    }

    public void listarCultos() {
        if (iglesiaSelected != null) {
            lstCulto = cultoFacade.listarCultosPorIglesia(iglesiaSelected);
        } else {
            lstCulto = cultoFacade.findAll();
        }
    }

    public void generarReporte(String tipo) throws JRException, IOException {
        switch ((10 * tipoReporte) + tipoSeccion) {
            case 11:
                generarReporteAsistenciaZona(tipo);
                break;
            case 12:
                generarReporteAsistenciaPais(tipo);
                break;
            case 13:
                generarReporteAsistenciaDepartamento(tipo);
                break;
            case 14:
                generarReporteAsistenciaMunicipio(tipo);
                break;
            case 15:
                generarReporteAsistenciaIglesia(tipo);
                break;
            case 16:
                generarReporteAsistenciaCulto(tipo);
                break;
            case 21:
                generarReporteBautismoZona(tipo);
                break;
            case 22:
                generarReporteBautismoPais(tipo);
                break;
            case 23:
                generarReporteBautismoDepartamento(tipo);
                break;
            case 24:
                generarReporteBautismoMunicipio(tipo);
                break;
            case 25:
                generarReporteBautismoIglesia(tipo);
                break;
            case 31:
                generarReporteBodaZona(tipo);
                break;
            case 32:
                generarReporteBodaPais(tipo);
                break;
            case 33:
                generarReporteBodaDepartamento(tipo);
                break;
            case 34:
                generarReporteBodaMunicipio(tipo);
                break;
            case 35:
                generarReporteBodaIglesia(tipo);
                break;
            case 41:
                generarReporteConversionZona(tipo);
                break;
            case 42:
                generarReporteConversionPais(tipo);
                break;
            case 43:
                generarReporteConversionDepartamento(tipo);
                break;
            case 44:
                generarReporteConversionMunicipio(tipo);
                break;
            case 45:
                generarReporteConversionIglesia(tipo);
                break;
            case 46:
                generarReporteConversionCulto(tipo);
                break;
            case 51:
                generarReporteOfrendaZona(tipo);
                break;
            case 52:
                generarReporteOfrendaPais(tipo);
                break;
            case 53:
                generarReporteOfrendaDepartamento(tipo);
                break;
            case 54:
                generarReporteOfrendaMunicipio(tipo);
                break;
            case 55:
                generarReporteOfrendaIglesia(tipo);
                break;
            case 56:
                generarReporteOfrendaCulto(tipo);
                break;
            case 61:
                generarReportePresentacionZona(tipo);
                break;
            case 62:
                generarReportePresentacionPais(tipo);
                break;
            case 63:
                generarReportePresentacionDepartamento(tipo);
                break;
            case 64:
                generarReportePresentacionMunicipio(tipo);
                break;
            case 65:
                generarReportePresentacionIglesia(tipo);
            case 75:
                generarReporteMisionIglesia(tipo);
            default:
        }
    }

    //Reporte Asistencia por Zona 
    public void generarReporteAsistenciaZona(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Asistencia/AsistenciaZona.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idZona", zonaSelected.getIdzona());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=AsistenciaZona.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Asistencia por Pais 
    public void generarReporteAsistenciaPais(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Asistencia/AsistenciaPais.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idPais", paisSelected.getIdpais());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=AsistenciaPais.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Asistencia por Departamento 
    public void generarReporteAsistenciaDepartamento(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Asistencia/AsistenciaDepartamento.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idDepartamento", departamentoSelected.getIddepartamento());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=AsistenciaDepartamento.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Asistencia por Municipio 
    public void generarReporteAsistenciaMunicipio(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Asistencia/AsistenciaMunicipio.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idMunicipio", municipioSelected.getIdmunicipio());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=AsistenciaMunicipio.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Asistencia por Iglesia 
    public void generarReporteAsistenciaIglesia(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Asistencia/AsistenciaIglesia.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idIglesia", iglesiaSelected.getIdiglesia());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=AsistenciaIglesia.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Asistencia por Culto 
    public void generarReporteAsistenciaCulto(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Asistencia/AsistenciaCulto.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idCulto1", cultoSelected.getIdculto());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=AsistenciaCulto.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Reporte Bautismo por Zona 
    public void generarReporteBautismoZona(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Bautismo/BautismoZona.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idZona", zonaSelected.getIdzona());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BautismoZona.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Bautismo por Pais 
    public void generarReporteBautismoPais(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Bautismo/BautismoPais.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idPais", paisSelected.getIdpais());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BautismoPais.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Bautismo por Departamento 
    public void generarReporteBautismoDepartamento(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Bautismo/BautismoDepartamento.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idDepartamento", departamentoSelected.getIddepartamento());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BautismoDepartamento.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Bautismo por Municipio 
    public void generarReporteBautismoMunicipio(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Bautismo/BautismoMunicipio.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idMunicipio", municipioSelected.getIdmunicipio());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BautismoMunicipio.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Bautismo por Iglesia 
    public void generarReporteBautismoIglesia(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Bautismo/BautismoIglesia.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idIglesia", iglesiaSelected.getIdiglesia());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BautismoIglesia.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Reporte Boda por Zona 
    public void generarReporteBodaZona(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Boda/BodaZona.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idZona", zonaSelected.getIdzona());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BodaZona.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Boda por Pais 
    public void generarReporteBodaPais(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Boda/BodaPais.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idPais", paisSelected.getIdpais());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BodaPais.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Boda por Departamento 
    public void generarReporteBodaDepartamento(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Boda/BodaDepartamento.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idDepartamento", departamentoSelected.getIddepartamento());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BodaDepartamento.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Boda por Municipio 
    public void generarReporteBodaMunicipio(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Boda/BodaMunicipio.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idMunicipio", municipioSelected.getIdmunicipio());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BodaMunicipio.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Boda por Iglesia 
    public void generarReporteBodaIglesia(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Boda/BodaIglesia.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idIglesia", iglesiaSelected.getIdiglesia());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=BodaIglesia.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Reporte Conversion por Zona 
    public void generarReporteConversionZona(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Conversion/ConversionZona.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idZona", zonaSelected.getIdzona());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=ConversionZona.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Conversion por Pais 
    public void generarReporteConversionPais(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Conversion/ConversionPais.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idPais", paisSelected.getIdpais());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=ConversionPais.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Conversion por Departamento 
    public void generarReporteConversionDepartamento(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Conversion/ConversionDepartamento.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idDepartamento", departamentoSelected.getIddepartamento());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=ConversionDepartamento.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Conversion por Municipio 
    public void generarReporteConversionMunicipio(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Conversion/ConversionMunicipio.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idMunicipio", municipioSelected.getIdmunicipio());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=ConversionMunicipio.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Conversion por Iglesia 
    public void generarReporteConversionIglesia(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Conversion/ConversionIglesia.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idIglesia", iglesiaSelected.getIdiglesia());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=ConversionIglesia.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Conversion por Culto 
    public void generarReporteConversionCulto(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Conversion/ConversionCulto.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idCulto", cultoSelected.getIdculto());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=ConversionCulto.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Reporte Ofrenda por Zona 
    public void generarReporteOfrendaZona(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Ofrenda/OfrendaZona.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idZona", zonaSelected.getIdzona());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=OfrendaZona.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Ofrenda por Pais 
    public void generarReporteOfrendaPais(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Ofrenda/OfrendaPais.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idPais", paisSelected.getIdpais());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=OfrendaPais.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Ofrenda por Departamento 
    public void generarReporteOfrendaDepartamento(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Ofrenda/OfrendaDepartamento.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idDepartamento", departamentoSelected.getIddepartamento());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=OfrendaDepartamento.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Ofrenda por Municipio 
    public void generarReporteOfrendaMunicipio(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Ofrenda/OfrendaMunicipio.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idMunicipio", municipioSelected.getIdmunicipio());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=OfrendaMunicipio.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Ofrenda por Iglesia 
    public void generarReporteOfrendaIglesia(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Ofrenda/OfrendaIglesia.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idIglesia", iglesiaSelected.getIdiglesia());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=OfrendaIglesia.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Ofrenda por Culto 
    public void generarReporteOfrendaCulto(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Ofrenda/OfrendaCulto.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idCulto", cultoSelected.getIdculto());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=OfrendaCulto.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Reporte Presentacion por Zona 
    public void generarReportePresentacionZona(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Presentacion/PresentacionZona.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idZona", zonaSelected.getIdzona());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=PresentacionZona.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Presentacion por Pais 
    public void generarReportePresentacionPais(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Presentacion/PresentacionPais.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idPais", paisSelected.getIdpais());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=PresentacionPais.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Presentacion por Departamento 
    public void generarReportePresentacionDepartamento(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Presentacion/PresentacionDepartamento.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idDepartamento", departamentoSelected.getIddepartamento());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=PresentacionDepartamento.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Presentacion por Municipio 
    public void generarReportePresentacionMunicipio(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Presentacion/PresentacionMunicipio.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idMunicipio", municipioSelected.getIdmunicipio());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=PresentacionMunicipio.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

//Reporte Presentacion por Iglesia 
    public void generarReportePresentacionIglesia(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Presentacion/PresentacionIglesia.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idIglesia", iglesiaSelected.getIdiglesia());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=PresentacionIglesia.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    //Reporte Misiones por Iglesia 
    public void generarReporteMisionIglesia(String tipo) throws JRException, IOException {
        ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportePath = sc.getRealPath("/Reportes/Datos/Misiones/MisionIglesia.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);
        parametros.put("idIglesia", iglesiaSelected.getIdiglesia());
        jasperPrint = JasperFillManager.fillReport(reportePath, parametros, conn);
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.setContentType("application/pdf");
        httpServletResponse.setHeader("Content-Disposition", "inline; filename=MisionIglesia.pdf");
        ServletOutputStream servletOutPutstream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutPutstream);
        FacesContext.getCurrentInstance().responseComplete();
    }
    
    public int getTipoSeccion() {
        return tipoSeccion;
    }

    public void setTipoSeccion(int tipoSeccion) {
        this.tipoSeccion = tipoSeccion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Zona getZonaSelected() {
        return zonaSelected;
    }

    public void setZonaSelected(Zona zonaSelected) {
        this.zonaSelected = zonaSelected;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
        this.setDepartamentoSelected(null);
    }

    public Departamentos getDepartamentoSelected() {
        return departamentoSelected;
    }

    public void setDepartamentoSelected(Departamentos departamentoSelected) {
        this.departamentoSelected = departamentoSelected;
        this.setMunicipioSelected(null);
    }

    public Municipio getMunicipioSelected() {
        return municipioSelected;
    }

    public void setMunicipioSelected(Municipio municipioSelected) {
        this.municipioSelected = municipioSelected;
        this.setIglesiaSelected(null);
    }

    public Iglesia getIglesiaSelected() {
        return iglesiaSelected;
    }

    public void setIglesiaSelected(Iglesia iglesiaSelected) {
        this.iglesiaSelected = iglesiaSelected;
        this.setCultoSelected(null);
    }

    public Culto getCultoSelected() {
        return cultoSelected;
    }

    public void setCultoSelected(Culto cultoSelected) {
        this.cultoSelected = cultoSelected;
    }

    public int getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(int tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public List<Departamentos> getLstDepartamento() {
        return lstDepartamento;
    }

    public void setLstDepartamento(List<Departamentos> lstDepartamento) {
        this.lstDepartamento = lstDepartamento;
    }

    public List<Zona> getLstZona() {
        return lstZona;
    }

    public void setLstZona(List<Zona> lstZona) {
        this.lstZona = lstZona;
    }

    public List<Pais> getLstPais() {
        return lstPais;
    }

    public void setLstPais(List<Pais> lstPais) {
        this.lstPais = lstPais;
    }

    public List<Municipio> getLstMunicipio() {
        return lstMunicipio;
    }

    public void setLstMunicipio(List<Municipio> lstMunicipio) {
        this.lstMunicipio = lstMunicipio;
    }

    public List<Iglesia> getLstIglesia() {
        return lstIglesia;
    }

    public void setLstIglesia(List<Iglesia> lstIglesia) {
        this.lstIglesia = lstIglesia;
    }

    public List<Culto> getLstCulto() {
        return lstCulto;
    }

    public void setLstCulto(List<Culto> lstCulto) {
        this.lstCulto = lstCulto;
    }

}
