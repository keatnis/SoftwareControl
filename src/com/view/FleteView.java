package com.view;

import com.dao.DetalleCombustibleDAO;
import com.dao.FleteDAO;
import com.dao.LugarTrabajoDAO;
import com.dao.TrabajadoresDAO;
import com.dao.VehiculosDAO;
import com.model.AsignacionUnidad;
import java.util.List;
import com.model.DetalleCombustible;
import com.model.Flete;
import com.model.Operador;
import com.model.Vehiculo;
import com.model.Workplace;
import java.util.Date;
import javax.swing.JComboBox;
import com.model.RecargaCombustible;
import com.model.Servicio;
import com.utils.ExportExcel;
import com.utils.Filter;
import com.utils.Validaciones;
import com.utils.table.RenderTable;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keatnis
 */
public class FleteView extends javax.swing.JPanel {

    private VehiculosDAO vehiculoDao;

    private Vehiculo vehiculo;
    private Operador operador;
    private Workplace workplace;

    private DetalleCombustible detalleCombustible;
    private DetalleCombustibleDAO detalleCombustibleDAO;
    private TrabajadoresDAO trabajadoresDAO;
    private LugarTrabajoDAO workplaceDAO;

    private AsignacionUnidad asignacionUnidad;
    private Flete flete;
    //  private LugarTrabajo lugarTrabajo;
    private RecargaCombustible recargaCombustible;

    private FleteDAO fleteDAO;

    public FleteView() {
        initComponents();
        this.detalleCombustibleDAO = new DetalleCombustibleDAO();
        this.vehiculoDao = new VehiculosDAO();
        this.trabajadoresDAO = new TrabajadoresDAO();
        this.fleteDAO = new FleteDAO();
        this.workplaceDAO = new LugarTrabajoDAO();

        this.showForms(false, true);
        this.showData(tblFlete);

        getTipoCombustible();

        getWorkplaces();
    }

    private void showData(JTable table) {
        Filter.removeAllRows(table);
        Object[] titles = new Object[]{
            "ID FLETE", "OPERADOR", "VEHICULO", "LUGAR TRABAJO", "CONCEPTO FLETE",
            "SALIDA", "RECIBE", "FECHA INICIO", "FECHA FIN", "STATUS", "KM INICIAL", "KM FINAL",};
        /*coloco el nombre de las  columnas de la tabla USER a el modelo */
        DefaultTableModel model = new DefaultTableModel(null, titles) {

            // desactivamos la opcion de editar los datos en la tabla
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        RenderTable render = new RenderTable();
        table.setDefaultRenderer(Object.class, render);
        /* obtengo la lista de */

        List<Flete> list = fleteDAO.getDataFlete();

        for (Flete flete : list) {

            model.addRow(new Object[]{
                flete.getId(),
                flete.getAsignacionUnidad().getOperador().getNombre() + " " + flete.getAsignacionUnidad().getOperador().getApePaterno(),
                flete.getAsignacionUnidad().getVehiculo().getMarca() + " Modelo: " + flete.getAsignacionUnidad().getVehiculo().getModelo() + " NO.SERIE: " + flete.getAsignacionUnidad().getVehiculo().getNumSerie(),
                flete.getLugarTrabajo().getClaveTrabajo() + " - " + flete.getLugarTrabajo().getNombreTrabajo() + " PERIODO: " + flete.getLugarTrabajo().getPeriodo(),
                flete.getConcepto(),
                flete.getLugarSalida(),
                flete.getRecibe(),
                flete.getAsignacionUnidad().getFechaInicio(),
                flete.getAsignacionUnidad().getFechaFin(),
                flete.getStatus(),
                flete.getAsignacionUnidad().getKmInicio(),
                flete.getAsignacionUnidad().getKmFinal()

            });
            /*establecemos el modelo  al Jtable llamado jTabla*/

        }

        // table.setRowHeight(30);
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.doLayout();
        /* asignamos el ancho de cada columna de la tabla*/

        int[] anchos = {80, 200, 300, 300, 150, 200, 150, 80, 200, 150, 150, 150};
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }

    }

    private void save() {

        flete = new Flete();
        asignacionUnidad = new AsignacionUnidad();
        asignacionUnidad.setVehiculo(vehiculo);
        asignacionUnidad.setOperador(operador);
        // asignacionUnidad.setOperador_id(this.operador.getId());

        Date da = startDate.getDate();
        asignacionUnidad.setFechaInicio(
                da
        );
        asignacionUnidad.setFechaFin(endDate.getDate());

        asignacionUnidad.setKmInicio(Float.parseFloat(txtKMIncicial.getText()));
        asignacionUnidad.setKmFinal(Float.parseFloat(txtKMFinal.getText()));
        //LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30); datatime
        // flete.setAsignacionUnidad(asignacionUnidad);
        recargaCombustible = new RecargaCombustible();
        recargaCombustible.setOdometroActual(Float.parseFloat(txtOdometroActual.getText()));
        recargaCombustible.setPrecioxlitro(detalleCombustible.getPrecio());
        recargaCombustible.setLitros(Float.parseFloat(txtLitrosCargados.getText()));
        recargaCombustible.setTipoCombustible(detalleCombustible.getTipo());
        recargaCombustible.setMonto(Float.parseFloat(txtMonto.getText()));

        recargaCombustible.setAsignacionUnidad(asignacionUnidad);
        String[] workString = cmbLugarTrabajo.getSelectedItem().toString().split(" ");
        if (workString.length < 0) {
            JOptionPane.showMessageDialog(null, "Lugar de trabajo no seleccionado");
        }
        workplace = new Workplace();
        workplace.setId(Integer.parseInt(workString[0]));
        flete.setLugarTrabajo(workplace);
        flete.setLugarSalida((String) cmbSalida.getSelectedItem());
        flete.setConcepto(txtConcepto.getText());
        flete.setRecibe((String) cmbRecibe.getSelectedItem());
        flete.setStatus((String) cmbStatusFlete.getSelectedItem());

        fleteDAO.saveFlete(flete, recargaCombustible, asignacionUnidad);
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(), ZoneId.systemDefault());
    }

    public void showForms(boolean showForm, boolean showList) {

        panelForm.setVisible(showForm);
        if (panelForm.isVisible()) {
            this.getAccessibleContext();
        }

        panelList.setVisible(showList);
    }

    private void getTipoCombustible() {
        this.cmbTipoCompustible.removeAllItems();

        List<DetalleCombustible> ve = detalleCombustibleDAO.getAllDetalles();

        for (DetalleCombustible vehiculol : ve) {

            this.cmbTipoCompustible.addItem(vehiculol.getTipo());

            detalleCombustible = new DetalleCombustible();
            detalleCombustible.setId(ve.get(cmbTipoCompustible.getSelectedIndex()).getId());
            detalleCombustible.setPrecio(ve.get(cmbTipoCompustible.getSelectedIndex()).getPrecio());
            detalleCombustible.setTipo(ve.get(cmbTipoCompustible.getSelectedIndex()).getTipo());

        }
        this.cmbTipoCompustible.repaint();

    }

    private void getWorkplaces() {
        this.cmbLugarTrabajo.removeAllItems();

        List<Workplace> ve = workplaceDAO.getWorkplaces();

        for (Workplace vehiculol : ve) {

            this.cmbLugarTrabajo.addItem(vehiculol.getId() + " " + vehiculol.getClaveTrabajo() + " " + vehiculol.getNombreTrabajo());

        }

        this.cmbLugarTrabajo.repaint();
        //cmbLugarTrabajo.setPopupVisible(true);

    }

    private void getVehiculo() {
        String res = (String) this.cmbVehiculo.getEditor().getItem();
        this.cmbVehiculo.removeAllItems();

        List<Vehiculo> ve = vehiculoDao.getByKey(res);

        for (Vehiculo vehiculol : ve) {

            cmbVehiculo.addItem(vehiculol.getId() + " MARCA: " + vehiculol.getMarca() + " MODELO: "
                    + vehiculol.getModelo() + " NUM_SERIE: " + vehiculol.getDescripcion());

        }
        vehiculo = new Vehiculo();
        vehiculo.setId(ve.get(cmbVehiculo.getSelectedIndex()).getId());
        vehiculo.setTipoCombustible(ve.get(cmbVehiculo.getSelectedIndex()).getTipoCombustible());
        this.cmbVehiculo.repaint();
        cmbVehiculo.setPopupVisible(true);

    }

    private void editAction() {

    }

    private void deleteAction() {

    }

    private void searchOperador() {
        this.cmbOperador.removeAllItems();
        String res = (String) this.cmbOperador.getEditor().getItem();
        List<Operador> ve = trabajadoresDAO.searchOperador(res);

        for (Operador operadorl : ve) {

            this.cmbOperador.addItem(operadorl.getNombre() + " " + operadorl.getApePaterno() + " " + operadorl.getApeMaterno());

//            operador.setNombre(operador.getNombre());
//            operador.setApePaterno(operador.getApePaterno());
//            operador.setApeMaterno(operador.getApeMaterno());
        }
        this.operador = new Operador();
        this.operador.setId(ve.get(cmbOperador.getSelectedIndex()).getId());
        this.cmbOperador.repaint();
        cmbOperador.setPopupVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoPago = new javax.swing.ButtonGroup();
        dateUtil1 = new com.toedter.calendar.DateUtil();
        panelForm = new javax.swing.JScrollPane();
        panelForm2 = new javax.swing.JPanel();
        asignacion = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbVehiculo = new javax.swing.JComboBox<>();
        cmbOperador = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnOperador = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtKMIncicial = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtKMFinal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        endDate = new com.toedter.calendar.JDateChooser();
        startDate = new com.toedter.calendar.JDateChooser();
        txtHora = new javax.swing.JFormattedTextField();
        combustible = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbGasolinera = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        fechaRecarga = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        rbEfectivo = new javax.swing.JRadioButton();
        rbTransferencia = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        cmbTipoCompustible = new javax.swing.JComboBox<>();
        txtLitrosCargados = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtOdometroActual = new javax.swing.JTextField();
        btnDetalleCombustible = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        detallesFlete = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cmbLugarTrabajo = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cmbStatusFlete = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cmbSalida = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cmbResponsableCarga = new javax.swing.JComboBox<>();
        cmbTrabajador = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConcepto = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        cmbRecibe = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        panelList = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFlete = new javax.swing.JTable();
        panelOptions = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        txtSearch = new com.utils.components.txtPlaceholder();

        panelForm.setToolTipText("");
        panelForm.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelForm2.setLayout(new javax.swing.BoxLayout(panelForm2, javax.swing.BoxLayout.Y_AXIS));

        asignacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignacion Uidad"));
        asignacion.setToolTipText("");

        jLabel4.setText("Vehiculo/Unidad");

        jLabel9.setText("Operador");

        cmbVehiculo.setEditable(true);
        cmbVehiculo.setToolTipText("Puede buscar por modelo, marca o por numero de serie");
        cmbVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cmbVehiculoMouseEntered(evt);
            }
        });

        cmbOperador.setEditable(true);
        cmbOperador.setToolTipText("Buscar por nombre");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnOperador.setText("Buscar");
        btnOperador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOperadorActionPerformed(evt);
            }
        });

        jLabel10.setText("* se mostraran las unidades disponibles");

        jLabel13.setText("Fecha Inicio");

        jLabel15.setText("KM inicial");

        txtKMIncicial.setText("0");
        txtKMIncicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKMIncicialKeyTyped(evt);
            }
        });

        jLabel14.setText("Fecha Fin");

        txtKMFinal.setText("0");
        txtKMFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKMFinalKeyTyped(evt);
            }
        });

        jLabel16.setText("KM final");

        endDate.setDateFormatString("dd/MM/yyyy");
        endDate.setMaximumSize(null);

        startDate.setDateFormatString("dd/MM/yyyy");
        startDate.setMaximumSize(null);

        try {
            txtHora.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout asignacionLayout = new javax.swing.GroupLayout(asignacion);
        asignacion.setLayout(asignacionLayout);
        asignacionLayout.setHorizontalGroup(
            asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(asignacionLayout.createSequentialGroup()
                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(asignacionLayout.createSequentialGroup()
                        .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(asignacionLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, asignacionLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(16, 16, 16)))
                        .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(asignacionLayout.createSequentialGroup()
                                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbVehiculo, javax.swing.GroupLayout.Alignment.LEADING, 0, 279, Short.MAX_VALUE)
                                    .addComponent(cmbOperador, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1)
                                    .addComponent(btnOperador)))
                            .addGroup(asignacionLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKMFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(asignacionLayout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtKMIncicial, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(289, Short.MAX_VALUE))
        );
        asignacionLayout.setVerticalGroup(
            asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(asignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(2, 2, 2)
                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOperador))
                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(asignacionLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16))
                    .addGroup(asignacionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(endDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel13))
                            .addComponent(txtHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, asignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtKMIncicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtKMFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(9, 9, 9))
        );

        panelForm2.add(asignacion);

        combustible.setBorder(javax.swing.BorderFactory.createTitledBorder("Recarga de combustible"));
        combustible.setToolTipText("");

        jLabel1.setText("Gasolinera");

        cmbGasolinera.setEditable(true);

        jLabel2.setText("Fecha:");

        fechaRecarga.setDateFormatString("dd/MM/yyyy");
        fechaRecarga.setMaximumSize(null);

        jLabel3.setText("Tipo de pago:");

        tipoPago.add(rbEfectivo);
        rbEfectivo.setText("Efectivo");

        tipoPago.add(rbTransferencia);
        rbTransferencia.setText("Transferencia/Deposito");

        jLabel5.setText("Tipo de Combustible:");

        cmbTipoCompustible.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCompustibleItemStateChanged(evt);
            }
        });

        txtLitrosCargados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLitrosCargadosKeyReleased(evt);
            }
        });

        txtMonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMontoKeyTyped(evt);
            }
        });

        jLabel6.setText("Litros Cargados");

        jLabel7.setText("Monto $");

        jLabel8.setText("Odometro actual:");

        txtOdometroActual.setText("0");

        btnDetalleCombustible.setText("Ajuste ");
        btnDetalleCombustible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleCombustibleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout combustibleLayout = new javax.swing.GroupLayout(combustible);
        combustible.setLayout(combustibleLayout);
        combustibleLayout.setHorizontalGroup(
            combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combustibleLayout.createSequentialGroup()
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, combustibleLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOdometroActual, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtLitrosCargados))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, combustibleLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, combustibleLayout.createSequentialGroup()
                                .addComponent(cmbTipoCompustible, 0, 136, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDetalleCombustible))
                            .addComponent(cmbGasolinera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaRecarga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbEfectivo)
                            .addComponent(rbTransferencia))))
                .addContainerGap(289, Short.MAX_VALUE))
        );
        combustibleLayout.setVerticalGroup(
            combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(combustibleLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbGasolinera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(rbEfectivo))
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(combustibleLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(rbTransferencia))
                    .addGroup(combustibleLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(fechaRecarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbTipoCompustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDetalleCombustible))
                .addGap(37, 37, 37)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLitrosCargados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(combustibleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOdometroActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        panelForm2.add(combustible);

        detallesFlete.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle flete"));

        jLabel11.setText("Lugar de trabajo");

        cmbLugarTrabajo.setEditable(true);
        cmbLugarTrabajo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        jLabel17.setText("Estatus");

        cmbStatusFlete.setEditable(true);
        cmbStatusFlete.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En espera", "Preparado", "Activo", "Terminado", "Bloqueado", "Descargado", " " }));

        jLabel18.setText("Lugar salida");

        cmbSalida.setEditable(true);
        cmbSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Almacen", " " }));

        jLabel19.setText("Responsable de carga");

        cmbResponsableCarga.setEditable(true);
        cmbResponsableCarga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));
        cmbResponsableCarga.setToolTipText("Buscar por nombre");

        cmbTrabajador.setText("Buscar");
        cmbTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTrabajadorActionPerformed(evt);
            }
        });

        jLabel21.setText("Concepto");

        txtConcepto.setColumns(20);
        txtConcepto.setRows(5);
        jScrollPane1.setViewportView(txtConcepto);

        jLabel20.setText("Recibe");

        cmbRecibe.setEditable(true);
        cmbRecibe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Destino 1", " " }));

        javax.swing.GroupLayout detallesFleteLayout = new javax.swing.GroupLayout(detallesFlete);
        detallesFlete.setLayout(detallesFleteLayout);
        detallesFleteLayout.setHorizontalGroup(
            detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detallesFleteLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel18)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbLugarTrabajo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbSalida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbStatusFlete, 0, 187, Short.MAX_VALUE)
                    .addComponent(cmbResponsableCarga, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbRecibe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(cmbTrabajador)
                .addContainerGap(115, Short.MAX_VALUE))
        );
        detallesFleteLayout.setVerticalGroup(
            detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detallesFleteLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(detallesFleteLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(cmbRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(detallesFleteLayout.createSequentialGroup()
                        .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbLugarTrabajo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(cmbSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(detallesFleteLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(detallesFleteLayout.createSequentialGroup()
                .addComponent(cmbStatusFlete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(detallesFleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbResponsableCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTrabajador))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detallesFlete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(detallesFlete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelForm2.add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout());

        btnCancel.setText("Cancelar");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancel);

        btnSave.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.default.focusColor"));
        btnSave.setFont(new java.awt.Font("Cantarell", 1, 15)); // NOI18N
        btnSave.setText("Guardar");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel2.add(btnSave);

        panelForm2.add(jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(977, 50));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 977, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        panelForm2.add(jPanel3);

        panelForm.setViewportView(panelForm2);

        tblFlete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblFlete);

        panelOptions.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));
        panelOptions.setLayout(new java.awt.GridLayout(7, 0, 0, 5));

        btnNew.setText("Nevo registro");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        panelOptions.add(btnNew);

        btnEdit.setText("editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        panelOptions.add(btnEdit);

        btnEliminar.setText("eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        panelOptions.add(btnEliminar);

        btnExportar.setText("exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        panelOptions.add(btnExportar);
        panelOptions.add(jSeparator1);

        jButton2.setText("Act. estado");
        panelOptions.add(jButton2);

        txtSearch.setPlaceholder("Buscar ...");
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelListLayout = new javax.swing.GroupLayout(panelList);
        panelList.setLayout(panelListLayout);
        panelListLayout.setHorizontalGroup(
            panelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 995, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(panelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListLayout.createSequentialGroup()
                    .addContainerGap(1012, Short.MAX_VALUE)
                    .addComponent(panelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        panelListLayout.setVerticalGroup(
            panelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListLayout.createSequentialGroup()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelListLayout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(panelOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(78, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 83, Short.MAX_VALUE)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 84, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(panelForm)
                .addGap(60, 60, 60))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDetalleCombustibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleCombustibleActionPerformed
        DetallesCombustible dialog = new DetallesCombustible(new javax.swing.JFrame(), true);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dialog.setVisible(false);
            }
        });
        dialog.setVisible(true);
        //  cmbTipoCompustible.removeAllItems();
        cmbTipoCompustible.repaint();
    }//GEN-LAST:event_btnDetalleCombustibleActionPerformed

    private void cmbTipoCompustibleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCompustibleItemStateChanged
        txtMonto.setText("");
        if (!txtMonto.getText().equals("")) {
            Float precioxLitro = detalleCombustible.getPrecio();
            Float litros = Float.parseFloat(txtLitrosCargados.getText());
            txtMonto.setText(litros * precioxLitro + "");
        }

    }//GEN-LAST:event_cmbTipoCompustibleItemStateChanged

    private void txtLitrosCargadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLitrosCargadosKeyReleased
        Float precioxLitro = detalleCombustible.getPrecio();
        Float litros = Float.parseFloat(txtLitrosCargados.getText());
        txtMonto.setText(String.format("%.2f", litros * precioxLitro));

    }//GEN-LAST:event_txtLitrosCargadosKeyReleased

    private void cmbVehiculoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbVehiculoMouseEntered
        cmbVehiculo.setToolTipText("pude buscar por modelo, marca y/o num de serie");
    }//GEN-LAST:event_cmbVehiculoMouseEntered

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getVehiculo();
        cmbTipoCompustible.setSelectedItem(vehiculo.getTipoCombustible());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnOperadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOperadorActionPerformed
        searchOperador();
    }//GEN-LAST:event_btnOperadorActionPerformed

    private void cmbTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTrabajadorActionPerformed
        searchTrabajador(cmbResponsableCarga);
    }//GEN-LAST:event_cmbTrabajadorActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (btnSave.getText().endsWith("ardar")) {
            save();
            clearAll();
            this.showForms(false, true);
            // this.showForm(list);
            this.showData(tblFlete);
            btnNew.setEnabled(true);
        } else if (btnSave.getText().startsWith("Actualizar")) {
            // update();
            clearAll();
            this.showForms(false, true);

            btnNew.setEnabled(true);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtKMIncicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKMIncicialKeyTyped
        Validaciones.soloRecibeNumeroConPunto(evt);
    }//GEN-LAST:event_txtKMIncicialKeyTyped

    private void txtKMFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKMFinalKeyTyped
        Validaciones.soloRecibeNumeroConPunto(evt);
    }//GEN-LAST:event_txtKMFinalKeyTyped

    private void txtMontoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMontoKeyTyped
        Validaciones.soloRecibeNumeroConPunto(evt);

    }//GEN-LAST:event_txtMontoKeyTyped

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.showForms(true, false);
        btnNew.setEnabled(false);
        btnSave.setText("guardar");
        btnSave.repaint();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (tblFlete.getSelectedRow() >= 0) {
            this.showForms(true, false);
            editAction();

        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.deleteAction();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        try {
            ExportExcel.exportarExcel(tblFlete);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        Filter.searchInTable(txtSearch, tblFlete);
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearAll();
        this.showForms(false, true);
        this.btnNew.setEnabled(true);
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel asignacion;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDetalleCombustible;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOperador;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cmbGasolinera;
    private javax.swing.JComboBox<String> cmbLugarTrabajo;
    private javax.swing.JComboBox<String> cmbOperador;
    private javax.swing.JComboBox<String> cmbRecibe;
    private javax.swing.JComboBox<String> cmbResponsableCarga;
    private javax.swing.JComboBox<String> cmbSalida;
    private javax.swing.JComboBox<String> cmbStatusFlete;
    private javax.swing.JComboBox<String> cmbTipoCompustible;
    private javax.swing.JButton cmbTrabajador;
    private javax.swing.JComboBox<String> cmbVehiculo;
    private javax.swing.JPanel combustible;
    private com.toedter.calendar.DateUtil dateUtil1;
    private javax.swing.JPanel detallesFlete;
    private com.toedter.calendar.JDateChooser endDate;
    private com.toedter.calendar.JDateChooser fechaRecarga;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane panelForm;
    private javax.swing.JPanel panelForm2;
    private javax.swing.JPanel panelList;
    private javax.swing.JPanel panelOptions;
    private javax.swing.JRadioButton rbEfectivo;
    private javax.swing.JRadioButton rbTransferencia;
    private com.toedter.calendar.JDateChooser startDate;
    private javax.swing.JTable tblFlete;
    private javax.swing.ButtonGroup tipoPago;
    private javax.swing.JTextArea txtConcepto;
    private javax.swing.JFormattedTextField txtHora;
    private javax.swing.JTextField txtKMFinal;
    private javax.swing.JTextField txtKMIncicial;
    private javax.swing.JTextField txtLitrosCargados;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtOdometroActual;
    private com.utils.components.txtPlaceholder txtSearch;
    // End of variables declaration//GEN-END:variables

    private void searchTrabajador(JComboBox comboBox) {
        comboBox.removeAllItems();
        String res = (String) comboBox.getEditor().getItem();
        List<Operador> ve = trabajadoresDAO.searchOperador(res);

        for (Operador operador : ve) {

            comboBox.addItem(operador.getNombre() + " " + operador.getApePaterno() + "");

            operador = new Operador();
            operador.setId(operador.getId());
            operador.setNombre(operador.getNombre());
            operador.setApePaterno(operador.getApePaterno());
            operador.setApeMaterno(operador.getApeMaterno());

        }
        comboBox.repaint();
    }

    private void clearAll() {
        txtConcepto.setText("");
        txtHora.setText("");
        txtKMFinal.setText("0");
        txtKMIncicial.setText("0");
        txtLitrosCargados.setText("0");
        txtMonto.setText("0");
        txtOdometroActual.setText("0");
        startDate.setDate(null);
        endDate.setDate(null);
        //  cmbGasolinera.setSelectedIndex(0);
        cmbLugarTrabajo.setSelectedIndex(0);
        //   cmbOperador.setSelectedIndex(0);
        cmbRecibe.setSelectedIndex(0);
        cmbResponsableCarga.setSelectedIndex(0);
        cmbSalida.setSelectedIndex(0);
        cmbStatusFlete.setSelectedIndex(0);
        cmbTipoCompustible.setSelectedIndex(0);
        // cmbTrabajador.setSelectedIndex(-1);
//      cmbVehiculo.setSelectedIndex(0);

    }
}