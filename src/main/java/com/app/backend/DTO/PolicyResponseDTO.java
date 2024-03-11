package com.app.backend.DTO;

public class PolicyResponseDTO {
    private PolicyInfo poliza;
    private EmployeeInfo empleado;
    private InventoryInfo detalleArticulo;

    // Constructores, getters y setters

    public PolicyResponseDTO() {
    }

    public PolicyResponseDTO(PolicyInfo poliza, EmployeeInfo empleado, InventoryInfo detalleArticulo) {
        this.poliza = poliza;
        this.empleado = empleado;
        this.detalleArticulo = detalleArticulo;
    }

    public PolicyInfo getPoliza() {
        return poliza;
    }

    public void setPoliza(PolicyInfo poliza) {
        this.poliza = poliza;
    }

    public EmployeeInfo getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmployeeInfo empleado) {
        this.empleado = empleado;
    }

    public InventoryInfo getDetalleArticulo() {
        return detalleArticulo;
    }

    public void setDetalleArticulo(InventoryInfo detalleArticulo) {
        this.detalleArticulo = detalleArticulo;
    }

    // Clases internas para representar la información específica
    public static class PolicyInfo {
        private Long IDPoliza;
        private int Cantidad;

        public PolicyInfo() {
        }

        public PolicyInfo(Long IDPoliza, int Cantidad) {
            this.IDPoliza = IDPoliza;
            this.Cantidad = Cantidad;
        }

        public Long getIDPoliza() {
            return IDPoliza;
        }

        public void setIDPoliza(Long IDPoliza) {
            this.IDPoliza = IDPoliza;
        }

        public int getCantidad() {
            return Cantidad;
        }

        public void setCantidad(int Cantidad) {
            this.Cantidad = Cantidad;
        }
    }

    public static class EmployeeInfo {

        private Long IdEmpleado;
        private String Nombre;
        private String Apellido;


        public EmployeeInfo() {
        }

        public EmployeeInfo(Long idEmpleado, String Nombre, String Apellido) {
            this.IdEmpleado = idEmpleado;
            this.Nombre = Nombre;
            this.Apellido = Apellido;
        }

        public String getNombre() {
            return Nombre;
        }

        public void setNombre(String Nombre) {
            this.Nombre = Nombre;
        }

        public String getApellido() {
            return Apellido;
        }

        public void setApellido(String Apellido) {
            this.Apellido = Apellido;
        }

        public Long getIdEmpleado() {
            return IdEmpleado;
        }

        public void setIdEmpleado(Long idEmpleado){this.IdEmpleado=idEmpleado;}
    }

    public static class InventoryInfo {
        private Long SKU;
        private String Nombre;

        public InventoryInfo() {
        }

        public InventoryInfo(Long SKU, String Nombre) {
            this.SKU = SKU;
            this.Nombre = Nombre;
        }

        public Long getSKU() {
            return SKU;
        }

        public void setSKU(Long SKU) {
            this.SKU = SKU;
        }

        public String getNombre() {
            return Nombre;
        }

        public void setNombre(String Nombre) {
            this.Nombre = Nombre;
        }
    }
}
