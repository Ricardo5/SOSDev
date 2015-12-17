//Paquete
package ptovta;

//Importaciones
import java.awt.Cursor;
import static ptovta.Princip.bIdle;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import ptovta.Login;
import ptovta.Star;




/*Catálogo de tallas y colores*/
public class SelectActivos extends javax.swing.JFrame 
{
    /*Declara variables originales*/
    private String          sMarcaOri;
    private String          sDescripMarcOri;
    private String          sModeOri;
    private String          sDescripModOri;
    
    /*Declara contadors globales*/
    private int             iContCellEd;
    private int             iContFi;
    
    private final JTextField            jTFCamp;
    /*Para controlar si seleccionar todo o deseleccionarlo de la tabla*/
    private boolean          bSel;

    /*Variable que contiene el borde actual*/
    private javax.swing.border.Border bBordOri;
    
    
    
    /*Constructor sin argumentos*/
    public SelectActivos(JTextField jCamp) 
    {
        /*Inicaliza los componentes gráficos*/
        initComponents();
        
        jTFCamp=jCamp;
        /*Para que las tabla tengan scroll horisontal*/
        jTab.setAutoResizeMode(0);
        
        /*Para que no se muevan las columnas*/
        jTab.getTableHeader().setReorderingAllowed(false);
        
        /*Esconde el link de ayuda*/
        jLAyu.setVisible(false);
        
        /*Centra la ventana*/
        this.setLocationRelativeTo(null);
        
        /*Establece el titulo de la ventana con El usuario, la fecha y hora*/                
        this.setTitle("Marcas y modelos, Usuario: <" + Login.sUsrG + "> " + Login.sFLog);        

        /*Inicialmente esta deseleccionada la tabla*/
        bSel        = false;
        
        //Establece el ícono de la forma
        Star.vSetIconFram(this);
        
        /*Para que la tabla este ordenada al mostrarce y al dar clic en el nombre de la columna*/
        TableRowSorter trs = new TableRowSorter<>((DefaultTableModel)jTab.getModel());
        jTab.setRowSorter(trs);
        trs.setSortsOnUpdates(true);
        
        /*Establece el tamaño de las columnas de la tabla*/
        jTab.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTab.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTab.getColumnModel().getColumn(2).setPreferredWidth(400);
        jTab.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTab.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        /*Activa en la tabla que se usen normamente las teclas de tabulador*/
        jTab.setFocusTraversalKeys(java.awt.KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        jTab.setFocusTraversalKeys(java.awt.KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
        
        /*Inicializa el contador de filas en uno*/
        iContFi      = 1;
        
        /*Incializa el contador del cell editor*/
        iContCellEd = 1;                
        
        /*Crea el listener para la tabla, para cuando se modifique una celda*/
        PropertyChangeListener pr = new PropertyChangeListener() 
        {
            @Override
            public void propertyChange(PropertyChangeEvent event) 
            {
                /*Si no hay selección entonces que regresa*/
                if(jTab.getSelectedRow()==-1)
                    return;
                
                /*Obtén la propiedad que a sucedio en el control*/
                String pro = event.getPropertyName();                                
                                
                /*Si el evento fue por entrar en una celda del tabla*/
                if("tableCellEditor".equals(pro)) 
                {
                    /*Si el contador de cell editor está en 1 entonces que lea el valor original que estaba ya*/
                    if(iContCellEd==1)
                    {                        
                        /*Obtén algunos datos originales de la fila*/                        
                        sMarcaOri            = jTab.getValueAt(jTab.getSelectedRow(), 1).toString();                                                                                  
                        sDescripMarcOri      = jTab.getValueAt(jTab.getSelectedRow(), 2).toString();                                                                                                          
                        sModeOri             = jTab.getValueAt(jTab.getSelectedRow(), 3).toString();                                                                                                          
                        sDescripModOri       = jTab.getValueAt(jTab.getSelectedRow(), 4).toString();  
                        
                        /*Aumenta en uno el contador de celleditor*/
                        ++iContCellEd;
                    }
                    /*Else, el contador de cell editor es 2, osea que va de salida*/
                    else
                    {
                        /*Reinicia el conteo*/
                        iContCellEd         = 1;
                            
                        /*Coloca los valores origianales*/
                        jTab.setValueAt(sMarcaOri,          jTab.getSelectedRow(), 1);
                        jTab.setValueAt(sDescripMarcOri,    jTab.getSelectedRow(), 2);
                        jTab.setValueAt(sModeOri,           jTab.getSelectedRow(), 3);
                        jTab.setValueAt(sDescripModOri,     jTab.getSelectedRow(), 4);
                    }
                                        
                }/*Fin de if("tableCellEditor".equals(property)) */
                
            }/*Fin de public void propertyChange(PropertyChangeEvent event) */            
        };
        
        /*Establece el listener para la tabla de marcs*/
        jTab.addPropertyChangeListener(pr);
                                
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
       //Carga todas las zonas 
        vCargarZonas(con);
        
        //Carga todas las clasificaciones
        vCargarClasificaciones(con);
        
        //Carga a los responsables
        vCargarResponsables(con);
        
        //Cierra la base de datos
        if(Star.iCierrBas(con)==-1)
            return;
            
        /*Obtén todas las tallas y colores de la base de datos*/
        vCarg();
            
    }/*Fin de public Marcs() */

    
   
    private void vCarg()
    {    
        /*Reinicia el contador de filas*/
        iContFi = 1;            
            
        /*Borra todos los item en la tabla*/
        DefaultTableModel dm = (DefaultTableModel)jTab.getModel();
        dm.setRowCount(0);
                                    
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos    
        Statement   st;
        ResultSet   rs;        
        String      sQ;
        
        /*Trae todas las marcas de la base de datos y cargalas en la tabla*/
        try
        {
            sQ = "SELECT referencia, descrip,zona, sucursal,clasificacion,responsable FROM activos";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces*/
            while(rs.next())
            {
                /*Agregalo a la tabla*/
                Object nu[]         = {iContFi, rs.getString("referencia"), rs.getString("descrip"), rs.getString("zona"), rs.getString("sucursal"), rs.getString("clasificacion"), rs.getString("responsable")};
                dm.addRow(nu);
                
                /*Aumenta en uno el contador de filas en uno*/
                ++iContFi;       
            }                        
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }            
        
        //Cierra la base de datos
        Star.iCierrBas(con);
        
    }/*Fin de private void vCarg()*/
            
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP1 = new javax.swing.JPanel();
        jBSal = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTab = new javax.swing.JTable();
        jLAyu = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jCSucursal = new javax.swing.JComboBox();
        jCZona = new javax.swing.JComboBox();
        jTZona = new javax.swing.JTextField();
        jTSucursal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jBBusc = new javax.swing.JButton();
        jBMosT = new javax.swing.JButton();
        jTBusc = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jCResponsable = new javax.swing.JComboBox();
        jCClasificacion = new javax.swing.JComboBox();
        jTClasificacion = new javax.swing.JTextField();
        jTResponsable = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jBCarg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jP1.setBackground(new java.awt.Color(255, 255, 255));
        jP1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jP1KeyPressed(evt);
            }
        });
        jP1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBSal.setBackground(new java.awt.Color(255, 255, 255));
        jBSal.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBSal.setForeground(new java.awt.Color(0, 102, 0));
        jBSal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/sal.png"))); // NOI18N
        jBSal.setText("Salir");
        jBSal.setToolTipText("Salir (ESC)");
        jBSal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBSal.setNextFocusableComponent(jCZona);
        jBSal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBSalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBSalMouseExited(evt);
            }
        });
        jBSal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalActionPerformed(evt);
            }
        });
        jBSal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBSalKeyPressed(evt);
            }
        });
        jP1.add(jBSal, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 140, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccion Activos");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jP1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 230, -1));

        jTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "#Referencia", "Descripción", "Zona", "Sucursal", "Clasificación", "Responsable"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTab.setGridColor(new java.awt.Color(255, 255, 255));
        jTab.setNextFocusableComponent(jBBusc);
        jTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabMouseClicked(evt);
            }
        });
        jTab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTab);

        jP1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 640, 280));

        jLAyu.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLAyu.setForeground(new java.awt.Color(0, 51, 204));
        jLAyu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLAyu.setText("http://Ayuda en Lìnea");
        jLAyu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLAyuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLAyuMouseExited(evt);
            }
        });
        jP1.add(jLAyu, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 120, 20));

        jLabel14.setText("Sucursal:");
        jP1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 60, -1));

        jCSucursal.setNextFocusableComponent(jCClasificacion);
        jCSucursal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCSucursalFocusLost(evt);
            }
        });
        jCSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSucursalActionPerformed(evt);
            }
        });
        jCSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCSucursalKeyPressed(evt);
            }
        });
        jP1.add(jCSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 90, 20));

        jCZona.setNextFocusableComponent(jCSucursal);
        jCZona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCZonaFocusLost(evt);
            }
        });
        jCZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCZonaActionPerformed(evt);
            }
        });
        jCZona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCZonaKeyPressed(evt);
            }
        });
        jP1.add(jCZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 90, 20));

        jTZona.setEditable(false);
        jTZona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTZona.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTZonaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTZonaFocusLost(evt);
            }
        });
        jTZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTZonaActionPerformed(evt);
            }
        });
        jTZona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTZonaKeyPressed(evt);
            }
        });
        jP1.add(jTZona, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 160, 20));

        jTSucursal.setEditable(false);
        jTSucursal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTSucursal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTSucursalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTSucursalFocusLost(evt);
            }
        });
        jTSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTSucursalActionPerformed(evt);
            }
        });
        jTSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTSucursalKeyPressed(evt);
            }
        });
        jP1.add(jTSucursal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 160, 20));

        jLabel17.setText("Zona:");
        jP1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 60, -1));

        jBBusc.setBackground(new java.awt.Color(255, 255, 255));
        jBBusc.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBBusc.setForeground(new java.awt.Color(0, 102, 0));
        jBBusc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/busc5.png"))); // NOI18N
        jBBusc.setText("Buscar F3");
        jBBusc.setNextFocusableComponent(jTBusc);
        jBBusc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBBuscMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBBuscMouseExited(evt);
            }
        });
        jBBusc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscActionPerformed(evt);
            }
        });
        jBBusc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBBuscKeyPressed(evt);
            }
        });
        jP1.add(jBBusc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, 140, 20));

        jBMosT.setBackground(new java.awt.Color(255, 255, 255));
        jBMosT.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBMosT.setForeground(new java.awt.Color(0, 102, 0));
        jBMosT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/mostt.png"))); // NOI18N
        jBMosT.setText("Mostrar F4");
        jBMosT.setToolTipText("Mostrar Nuevamente todos los Registros");
        jBMosT.setNextFocusableComponent(jBSal);
        jBMosT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBMosTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBMosTMouseExited(evt);
            }
        });
        jBMosT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMosTActionPerformed(evt);
            }
        });
        jBMosT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBMosTKeyPressed(evt);
            }
        });
        jP1.add(jBMosT, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 430, 140, 20));

        jTBusc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTBusc.setNextFocusableComponent(jBMosT);
        jTBusc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTBuscFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTBuscFocusLost(evt);
            }
        });
        jTBusc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTBuscKeyPressed(evt);
            }
        });
        jP1.add(jTBusc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 250, 20));

        jLabel15.setText("Responsable:");
        jP1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 70, -1));

        jCResponsable.setNextFocusableComponent(jBBusc);
        jCResponsable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCResponsableFocusLost(evt);
            }
        });
        jCResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCResponsableActionPerformed(evt);
            }
        });
        jCResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCResponsableKeyPressed(evt);
            }
        });
        jP1.add(jCResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 90, 20));

        jCClasificacion.setNextFocusableComponent(jCResponsable);
        jCClasificacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCClasificacionFocusLost(evt);
            }
        });
        jCClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCClasificacionActionPerformed(evt);
            }
        });
        jCClasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jCClasificacionKeyPressed(evt);
            }
        });
        jP1.add(jCClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 90, 20));

        jTClasificacion.setEditable(false);
        jTClasificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTClasificacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTClasificacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTClasificacionFocusLost(evt);
            }
        });
        jTClasificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTClasificacionActionPerformed(evt);
            }
        });
        jTClasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTClasificacionKeyPressed(evt);
            }
        });
        jP1.add(jTClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 160, 20));

        jTResponsable.setEditable(false);
        jTResponsable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        jTResponsable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTResponsableFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTResponsableFocusLost(evt);
            }
        });
        jTResponsable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTResponsableActionPerformed(evt);
            }
        });
        jTResponsable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTResponsableKeyPressed(evt);
            }
        });
        jP1.add(jTResponsable, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 160, 20));

        jLabel18.setText("Clasificación:");
        jP1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 70, -1));

        jBCarg.setBackground(new java.awt.Color(255, 255, 255));
        jBCarg.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jBCarg.setForeground(new java.awt.Color(0, 102, 0));
        jBCarg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/carg.png"))); // NOI18N
        jBCarg.setText("Aceptar");
        jBCarg.setToolTipText("Aceptar (ENTER)");
        jBCarg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jBCarg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBCargMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBCargMouseExited(evt);
            }
        });
        jBCarg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargActionPerformed(evt);
            }
        });
        jBCarg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBCargKeyPressed(evt);
            }
        });
        jP1.add(jBCarg, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 140, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

          
    /*Cuando se presiona una tecla en el formulario*/
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_formKeyPressed
  
        
    /*Cuando se presiona una tecla en el panel*/
    private void jP1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jP1KeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_jP1KeyPressed

    
    /*Cuando se presiona el botón de salir*/
    private void jBSalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalActionPerformed
        
        /*Llama al recolector de basura y cierra la forma*/
        System.gc();                    
        this.dispose();                    
        
    }//GEN-LAST:event_jBSalActionPerformed

    
    /*Cuando se presiona una tecla en el botón salir*/
    private void jBSalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBSalKeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_jBSalKeyPressed

                   
    /*Cuando se presiona una  tecla en la tabla de marcs*/
    private void jTabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabKeyPressed
        
        //Llama a la función escalable
        vKeyPreEsc(evt);
        
    }//GEN-LAST:event_jTabKeyPressed
    
            
    /*Cuando se mueve la rueda del ratón en la forma*/
    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
    }//GEN-LAST:event_formMouseWheelMoved

    
    /*Cuando se arrastra el mouse en la forma*/
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
    }//GEN-LAST:event_formMouseDragged

    
    /*Cuando se mueve el ratón en la forma*/
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
    }//GEN-LAST:event_formMouseMoved
        
    
          
    /*Cuando el mouse entra en el campo del link de ayuda*/
    private void jLAyuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLAyuMouseEntered
        
        /*Cambia el cursor del ratón*/
        this.setCursor( new Cursor(Cursor.HAND_CURSOR));
        
    }//GEN-LAST:event_jLAyuMouseEntered

    
    /*Cuando el mouse sale del campo del link de ayuda*/
    private void jLAyuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLAyuMouseExited
        
        /*Cambia el cursor del ratón al que tenía*/
        this.setCursor( new Cursor(Cursor.DEFAULT_CURSOR));	
        
    }//GEN-LAST:event_jLAyuMouseExited

    
    
    
    /*Cuando se esta saliendo de la forma*/
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        /*Presiona el botón de salir*/
        jBSal.doClick();
        
    }//GEN-LAST:event_formWindowClosing

        
                
    /*Cuando el mouse entra en el botón específico*/
    private void jBSalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBSalMouseEntered
        
        /*Cambia el color del fondo del botón*/
        jBSal.setBackground(Star.colBot);
        
    }//GEN-LAST:event_jBSalMouseEntered

        
                
    /*Cuando el mouse sale del botón específico*/
    private void jBSalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBSalMouseExited
        
        /*Cambia el color del fondo del botón al original*/
        jBSal.setBackground(Star.colOri);
        
    }//GEN-LAST:event_jBSalMouseExited

    
    /*Cuando sucede una acción en el combo de los modelos*/
    private void jCSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSucursalActionPerformed

        /*Si no hay datos entonces regresa*/
        if(jCSucursal.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM sucursal WHERE cod = '" + jCSucursal.getSelectedItem().toString() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTSucursal.setText(rs.getString("descrip"));
            else
                jTSucursal.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTSucursal.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
        
        filtrar();
    }//GEN-LAST:event_jCSucursalActionPerformed

    
    /*Cuando se presiona una tecla en el combo de los colores*/
    private void jCSucursalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCSucursalKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jCSucursalKeyPressed

    
    /*Cuando sucede una acción en el combobox de marcas*/
    private void jCZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCZonaActionPerformed

        /*Si no hay datos entonces regresa*/
        if(jCZona.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM zona WHERE cod = '" + jCZona.getSelectedItem().toString().trim() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTZona.setText(rs.getString("descrip"));
            else
                jTZona.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTZona.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
       
        vCargaSucursales();
        
        filtrar();
    }//GEN-LAST:event_jCZonaActionPerformed

    
    /*Cuando se presiona una tecla en el combo de tallas*/
    private void jCZonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCZonaKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jCZonaKeyPressed

    
    /*Cuando se gana el foco del teclado en el campo de la descripción de la Marca*/
    private void jTZonaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTZonaFocusGained

        /*Selecciona todo el texto cuando gana el foco*/
        jTZona.setSelectionStart(0);jTZona.setSelectionEnd(jTZona.getText().length());

    }//GEN-LAST:event_jTZonaFocusGained

    
    /*Cuando se pierde el foco del teclado en el campo de la descripción de la marca*/
    private void jTZonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTZonaFocusLost

        /*Coloca el caret en la posiciòn 0*/
        jTZona.setCaretPosition(0);

    }//GEN-LAST:event_jTZonaFocusLost

    
    /*Cuando se presiona una tecla en el campo de la descripción de la talla*/
    private void jTZonaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTZonaKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jTZonaKeyPressed

    
    /*Cuando se gana el foco del teclado en el campo de la descripción del color*/
    private void jTSucursalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTSucursalFocusGained

        /*Selecciona todo el texto cuando gana el foco*/
        jTSucursal.setSelectionStart(0);jTSucursal.setSelectionEnd(jTSucursal.getText().length());

    }//GEN-LAST:event_jTSucursalFocusGained

    
    /*Cuando se pierde el foco del teclado en el campo de la descripción del color*/
    private void jTSucursalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTSucursalFocusLost

        /*Coloca el caret en la posiciòn 0*/
        jTSucursal.setCaretPosition(0);

    }//GEN-LAST:event_jTSucursalFocusLost

    
    /*Cuando se presiona una tecla en el campo de la descripción del color*/
    private void jTSucursalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTSucursalKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jTSucursalKeyPressed

    
    
    
    
    
    
    
    
    
    /*Cuando se pierde el foco del teclado en el combo de las marcas*/
    private void jCZonaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCZonaFocusLost
        
        /*Coloca el borde negro si tiene datos*/
        if(jCZona.getSelectedItem().toString().compareTo("")!=0)
            jCZona.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));
        
    }//GEN-LAST:event_jCZonaFocusLost

    
    /*Cuando el mouse entra en el botón de búscar*/
    private void jBBuscMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBBuscMouseEntered

        /*Cambia el color del fondo del botón*/
        jBBusc.setBackground(Star.colBot);

        /*Guardar el borde original que tiene el botón*/
        bBordOri    = jBBusc.getBorder();

        /*Aumenta el grosor del botón*/
        jBBusc.setBorder(new javax.swing.border.LineBorder(java.awt.Color.GREEN, 3));

    }//GEN-LAST:event_jBBuscMouseEntered

    
    /*Cuando el mouse sale del botón de búscar*/
    private void jBBuscMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBBuscMouseExited

        /*Cambia el color del fondo del botón al original*/
        jBBusc.setBackground(Star.colOri);

        /*Coloca el borde que tenía*/
        jBBusc.setBorder(bBordOri);

    }//GEN-LAST:event_jBBuscMouseExited

    
    /*Cuando se presiona el botón de búscar*/
    private void jBBuscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscActionPerformed

        /*Si el campo de buscar esta vacio no puede seguir*/
        if(jTBusc.getText().compareTo("")==0)
        {
            /*Coloca el borde rojo*/
            jTBusc.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.RED));

            /*Mensajea*/
            JOptionPane.showMessageDialog(null, "El campo de búsqueda esta vacio.", "Campo Vacio", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource(Star.sRutIconAd)));

            /*Coloca el foco del teclado en el campo de búsqueda y regresa*/
            jTBusc.grabFocus();
            return;
        }
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        /*Borra todos los item en la tabla de almacenes*/
        DefaultTableModel dm = (DefaultTableModel)jTab.getModel();
        dm.setRowCount(0);

        /*Resetea el contador de filas*/
        iContFi              = 1;

        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene de la base EL el filtrado necesario*/
        try
        {
            
            sQ = "Select referencia, descrip,zona, sucursal,clasificacion,responsable from"+
                 " activos "+
                 " WHERE referencia LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+ 
                 " OR descrip LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR zona LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR sucursal LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR clasificacion LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')"+
                 " OR responsable LIKE('%" + jTBusc.getText().replace(" ", "%") + "%')";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos*/
            while(rs.next())
            {
                /*Agregalos en la tabla*/
                DefaultTableModel te    = (DefaultTableModel)jTab.getModel();
               Object nu[]         = {iContFi, rs.getString("referencia"), rs.getString("descrip"), rs.getString("zona"), rs.getString("sucursal"), rs.getString("clasificacion"), rs.getString("responsable")};
                te.addRow(nu);

                /*Aumenta en uno el contador de filas*/
                ++iContFi;
            }
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        //Cierra la base de datos
        if(Star.iCierrBas(con)==-1)
            return;

        /*Vuelve a poner el foco del teclaod en el campo de buscar*/
        jTBusc.grabFocus();

    }//GEN-LAST:event_jBBuscActionPerformed

    
    /*Cuando se presiona una tecal en el botón de búscar*/
    private void jBBuscKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBBuscKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jBBuscKeyPressed

    
    /*Cuando el mouse entra en el botón de mostrar todo*/
    private void jBMosTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBMosTMouseEntered

        /*Cambia el color del fondo del botón*/
        jBMosT.setBackground(Star.colBot);

    }//GEN-LAST:event_jBMosTMouseEntered

    
    /*Cuando el mouse sale del botón de mostrar todo*/
    private void jBMosTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBMosTMouseExited

        /*Cambia el color del fondo del botón al original*/
        jBMosT.setBackground(Star.colOri);

    }//GEN-LAST:event_jBMosTMouseExited

    
    /*Cuando se presiona el botón de mostrar todo*/
    private void jBMosTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMosTActionPerformed

        /*Función para cargar nuevamente todos los registros en la tabla*/
        vCarg();

    }//GEN-LAST:event_jBMosTActionPerformed

    
    /*Cuando se presiona una tecal en el botón de mostrar todo*/
    private void jBMosTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBMosTKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jBMosTKeyPressed

    
    /*Cuando se gana el foco del teclado en el campo de búscar*/
    private void jTBuscFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBuscFocusGained

        /*Establece el botón por default*/
        this.getRootPane().setDefaultButton(jBBusc);
        
        /*Selecciona todo el texto cuando gana el foco*/
        jTBusc.setSelectionStart(0);jTBusc.setSelectionEnd(jTBusc.getText().length());

    }//GEN-LAST:event_jTBuscFocusGained

    
    /*Cundo se pierde el foco del teclado en el campo de búscar*/
    private void jTBuscFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTBuscFocusLost

        
        /*Coloca el cursor al principio del control*/
        jTBusc.setCaretPosition(0);

        /*Coloca el borde negro si tiene datos, caso contrario de rojo*/
        if(jTBusc.getText().compareTo("")!=0)
            jTBusc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));

    }//GEN-LAST:event_jTBuscFocusLost

    
    /*Cuando se presiona una tecla en el campo de búscar*/
    private void jTBuscKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTBuscKeyPressed

        /*Si se presionó enter entonces presiona el botón de búsqueda y regresa*/
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            jBBusc.doClick();
            return;
        }

        //Llama a la función escalable
        vKeyPreEsc(evt);

    }//GEN-LAST:event_jTBuscKeyPressed

    private void jTSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTSucursalActionPerformed

    private void jCResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCResponsableActionPerformed
          /*Si no hay datos entonces regresa*/
        if(jCResponsable.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM responsable WHERE cod = '" + jCResponsable.getSelectedItem().toString().trim() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTResponsable.setText(rs.getString("descrip"));
            else
                jTResponsable.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTResponsable.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
        // TODO add your handling code here:
        
        filtrar();
    }//GEN-LAST:event_jCResponsableActionPerformed

    private void jCResponsableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCResponsableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCResponsableKeyPressed

    private void jCClasificacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCClasificacionFocusLost
        if(jCClasificacion.getSelectedItem().toString().compareTo("")!=0)
            jCClasificacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));            // TODO add your handling code here:
    }//GEN-LAST:event_jCClasificacionFocusLost

    private void jCClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCClasificacionActionPerformed
         /*Si no hay datos entonces regresa*/
        if(jCClasificacion.getSelectedItem()==null)
            return;
        
        //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene la descripción de la talla en base a su código*/
        try
        {
            sQ = "SELECT descrip FROM clasificacion WHERE cod = '" + jCClasificacion.getSelectedItem().toString().trim() + "'";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces coloca el valor en el campo*/
            if(rs.next())
                jTClasificacion.setText(rs.getString("descrip"));
            else
                jTClasificacion.setText("");
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }

        /*Coloca al principio del control el caret*/
        jTClasificacion.setCaretPosition(0);

        //Cierra la base de datos
        Star.iCierrBas(con);
        
        filtrar();
    }//GEN-LAST:event_jCClasificacionActionPerformed

    private void jCClasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCClasificacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCClasificacionKeyPressed

    private void jTClasificacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTClasificacionFocusGained
         /*Selecciona todo el texto cuando gana el foco*/
        jTClasificacion.setSelectionStart(0);jTClasificacion.setSelectionEnd(jTClasificacion.getText().length());
    }//GEN-LAST:event_jTClasificacionFocusGained

    private void jTClasificacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTClasificacionFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClasificacionFocusLost

    private void jTClasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTClasificacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClasificacionKeyPressed

    private void jTResponsableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTResponsableFocusGained
       jTResponsable.setSelectionStart(0);jTResponsable.setSelectionEnd(jTResponsable.getText().length());
    }//GEN-LAST:event_jTResponsableFocusGained

    private void jTResponsableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTResponsableFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTResponsableFocusLost

    private void jTResponsableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTResponsableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTResponsableActionPerformed

    private void jTResponsableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTResponsableKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTResponsableKeyPressed

    private void jTZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTZonaActionPerformed

    private void jTClasificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTClasificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTClasificacionActionPerformed

    private void jCSucursalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCSucursalFocusLost
        /*Coloca el borde negro si tiene datos*/
        if(jCSucursal.getSelectedItem().toString().compareTo("")!=0)
            jCSucursal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));        // TODO add your handling code here:
    }//GEN-LAST:event_jCSucursalFocusLost

    private void jCResponsableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCResponsableFocusLost
         if(jCResponsable.getSelectedItem().toString().compareTo("")!=0)
            jCResponsable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,255)));         // TODO add your handling code here:
    }//GEN-LAST:event_jCResponsableFocusLost

    private void jBCargMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBCargMouseEntered

        /*Cambia el color del fondo del botón*/
        jBCarg.setBackground(Star.colBot);

    }//GEN-LAST:event_jBCargMouseEntered

    private void jBCargMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBCargMouseExited
          jBSal.setBackground(Star.colOri);
        /*Cambia el color del fondo del botón al original*/
        

    }//GEN-LAST:event_jBCargMouseExited

    private void jBCargActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargActionPerformed

        /*Si no hay selección en la tabla entonces*/
        if(jTab.getSelectedRow()==-1)
        {
            return;
        }
         if(jTFCamp!=null){
            jTFCamp.setText(jTab.getValueAt(jTab.getSelectedRow(), 1).toString());
            System.gc();                    
            this.dispose();     
         }
            

        

    }//GEN-LAST:event_jBCargActionPerformed

    private void jBCargKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBCargKeyPressed

        //Llama a la función escalable
        vKeyPreEsc(evt);
    }//GEN-LAST:event_jBCargKeyPressed

    private void jTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabMouseClicked
         if(evt.getClickCount() == 2) 
            jBCarg.doClick();        // TODO add your handling code here:
    }//GEN-LAST:event_jTabMouseClicked
    
    
    /*Función escalable para cuando se presiona una tecla en el módulo*/
    void vKeyPreEsc(java.awt.event.KeyEvent evt)
    {
        /*Pon la bandera para saber que ya hubó un evento y no se desloguie*/
        bIdle   = true;
        
        /*Si se presiona la tecla de escape presiona el botón de salir*/
        if(evt.getKeyCode() == KeyEvent.VK_ESCAPE) 
            jBSal.doClick();
        /*Else if se presiona Alt + F4 entonces presiona el botón de salir*/
        else if(evt.isAltDown() && evt.getKeyCode() == KeyEvent.VK_F4)
            jBSal.doClick();        
        /*Else if se F4 entonces presiona el botón de mostrar todos los registrod*/
        else if(evt.getKeyCode() == KeyEvent.VK_F4)
            jBMosT.doClick();        
        /*Else if se F3 entonces presiona el botón de búscar todos los registrod*/
        else if(evt.getKeyCode() == KeyEvent.VK_F3)
            jBBusc.doClick();        
    }
    
    /*Carga las marcas en el combo*/        
    private void vCargarZonas(Connection con)
    {
        /*Borra los items en el combobox*/
        jCZona.removeAllItems();
        
        /*Agrega cadena vacia*/

        jCZona.addItem("");
        

        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene las marcas y cargalas en el combo*/        
        try
        {
            sQ = "SELECT cod FROM zona";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces cargalas en el conrol*/
            while(rs.next())
                jCZona.addItem(rs.getString("cod"));            
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);            
        }    
        
        
    }/*Fin de private void vCargarZonas(Connection con)*/
    
     private void vCargarClasificaciones(Connection con)
    {
        /*Borra los items en el combobox*/
        jCClasificacion.removeAllItems();
        
        /*Agrega cadena vacia*/
        try{
        jCClasificacion.addItem("");
        }
        catch(Exception ex)
        {
            
        }
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene las marcas y cargalas en el combo*/        
        try
        {
            sQ = "SELECT cod FROM clasificacion";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces cargalas en el conrol*/
            while(rs.next())
                jCClasificacion.addItem(rs.getString("cod"));            
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);            
        }    
        
        
    }/*Fin de private void vCargarZonas(Connection con)*/
    
      private void vCargarResponsables(Connection con)
    {
        /*Borra los items en el combobox*/
        jCResponsable.removeAllItems();
        
        /*Agrega cadena vacia*/
        jCResponsable.addItem("");
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene las marcas y cargalas en el combo*/        
        try
        {
            sQ = "SELECT cod FROM responsable";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos entonces cargalas en el conrol*/
            while(rs.next())
                jCResponsable.addItem(rs.getString("cod"));            
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);            
        }    
        
        
    }/*Fin de private void vCargarZonas(Connection con)*/
     
    /*Carga todos los Modelos en el combo*/        
    private void vCargaSucursales()
    {
         Connection  con = Star.conAbrBas(true, false); 
        /*Borra los items en el combobox*/
        jCSucursal.removeAllItems();
        
        /*Agrega cadena vacia*/
        
            jCSucursal.addItem("");
        
      
        
        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;                 
        
        /*Obtiene todos los Modelos de la base de datos*/        
        try
        {
            sQ  = "SELECT cod FROM sucursal WHERE codZona= '"+jCZona.getSelectedItem().toString().trim()  +"'";
            st  = con.createStatement();
            rs  = st.executeQuery(sQ);
            /*Si hay datos entonces cargalos en el combo*/
            while(rs.next())
                jCSucursal.addItem(rs.getString("cod"));
            
        }
        catch(SQLException expnSQL)
        {
                /*Agrega cadena vacia*/
            jCSucursal.addItem("");
        }    
        
    }/*Fin de private void vCargaSucursales(Connection con)*/
    
    public void filtrar(){
        
         //Abre la base de datos        
        Connection  con = Star.conAbrBas(true, false);  
        
        //Si hubo error entonces regresa
        if(con==null)
            return;
        
        /*Borra todos los item en la tabla de almacenes*/
        DefaultTableModel dm = (DefaultTableModel)jTab.getModel();
        dm.setRowCount(0);

        /*Resetea el contador de filas*/
        iContFi              = 1;

        //Declara variables de la base de datos
        Statement   st;
        ResultSet   rs;        
        String      sQ;

        /*Obtiene de la base EL el filtrado necesario*/
        try
        {
            
            sQ = "Select referencia, descrip,zona, sucursal,clasificacion,responsable from"+
                 " activos "+
                 " WHERE CASE WHEN '" +jCZona.getSelectedItem().toString() +"' = '' THEN 1=1 ELSE zona ='" +jCZona.getSelectedItem().toString()+"' END"+ 
                 " AND CASE WHEN '" +jCSucursal.getSelectedItem().toString() +"' = '' THEN 1=1 ELSE sucursal ='" +jCSucursal.getSelectedItem().toString()+"' END"+ 
                 " AND CASE WHEN '" +jCClasificacion.getSelectedItem().toString() +"' = '' THEN 1=1 ELSE clasificacion ='" +jCClasificacion.getSelectedItem().toString()+"' END"+ 
                 " AND CASE WHEN '" +jCResponsable.getSelectedItem().toString() +"' = '' THEN 1=1 ELSE responsable ='" +jCResponsable.getSelectedItem().toString()+"' END";
            st = con.createStatement();
            rs = st.executeQuery(sQ);
            /*Si hay datos*/
            while(rs.next())
            {
                /*Agregalos en la tabla*/
                DefaultTableModel te    = (DefaultTableModel)jTab.getModel();
               Object nu[]         = {iContFi, rs.getString("referencia"), rs.getString("descrip"), rs.getString("zona"), rs.getString("sucursal"), rs.getString("clasificacion"), rs.getString("responsable")};
                te.addRow(nu);

                /*Aumenta en uno el contador de filas*/
                ++iContFi;
            }
        }
        catch(SQLException expnSQL)
        {
            //Procesa el error y regresa
            Star.iErrProc(this.getClass().getName() + " " + expnSQL.getMessage(), Star.sErrSQL, expnSQL.getStackTrace(), con);
            return;                        
        }
        catch(NullPointerException ex)
        {
            
        }

        //Cierra la base de datos
        if(Star.iCierrBas(con)==-1)
            return;
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBusc;
    private javax.swing.JButton jBCarg;
    private javax.swing.JButton jBMosT;
    private javax.swing.JButton jBSal;
    private javax.swing.JComboBox jCClasificacion;
    private javax.swing.JComboBox jCResponsable;
    private javax.swing.JComboBox jCSucursal;
    private javax.swing.JComboBox jCZona;
    private javax.swing.JLabel jLAyu;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jP1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTBusc;
    private javax.swing.JTextField jTClasificacion;
    private javax.swing.JTextField jTResponsable;
    private javax.swing.JTextField jTSucursal;
    private javax.swing.JTextField jTZona;
    private javax.swing.JTable jTab;
    // End of variables declaration//GEN-END:variables

}/*Fin de public class Clientes extends javax.swing.JFrame */
