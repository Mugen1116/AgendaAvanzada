package vista;

import controlador.cliente.ClienteController;
import modelo.cliente.Cliente;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.SortedMap;

public class ClienteTableModel implements TableModel {
    /** The TableModel column names. */
    public final String columnNames[] =
            new String[] {
                            "Nombre", "NIF", "Direccion",
                            "e-mail", "Fecha Alta", "Tarifa"
                         };

    private LinkedList<Cliente> clientes = new LinkedList<>();

    /** The list of TableModelListeners. */
    private final ArrayList<TableModelListener> tableModelListenerList = new ArrayList<TableModelListener>();
    /** The manager containing all the Student instances. */
    private final ClienteController clienteController;


    public ClienteTableModel(ClienteController clienteController) {
        super();
        this.clienteController = clienteController;
    }


    public Class<?> getColumnClass(int columnIndex) {
        return null;
    }


    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        return (columnIndex < columnNames.length) ? columnNames[columnIndex] : null;
    }

    public int getRowCount() {
        return clienteController.getClientes().size();
    }


    public void setValueAt(Object value, int fila, int columna) {
//        Cliente cli = (Cliente) clientes.get( fila );
//        HashMap<String, Cliente> clientesMap = clienteController.getClientes();
//        String[] clienteArray = clientesMap.keySet().toArray(new String[clientesMap.keySet().size()]);
//        Cliente cliente = clientesMap.get(clienteArray[fila] );
//        final Object result;
//
//
//        switch (columna) {
////            "Nombre", "NIF", "Direccion",
////                    "e-mail", "Fecha Alta", "Tarifa"
//            case 0:
//                result = cliente.getNombre();
//                break;
//            case 1:
//                result = cliente.getDireccion().toString();
//                break;
//            case 2:
//                result = cliente.getEmail();
//                break;
//            case 3:
//                result = cliente.getFechaAlta().toString();
//                break;
//            case 4:
//                result = cliente.getTarifa().toString();
//                break;
//            default:
//                result = null;
//        }

    }


    public Object getValueAt(int fila, int columna) {

        HashMap<String, Cliente> clientesMap = clienteController.getClientes();
        String[] clienteArray = clientesMap.keySet().toArray(new String[clientesMap.keySet().size()]);
        Cliente cliente = clientesMap.get(clienteArray[fila] );
        final Object result;
        switch (columna) {
//            "Nombre", "NIF", "Direccion",
//                    "e-mail", "Fecha Alta", "Tarifa"
            case 0:
                result = cliente.getNombre();
                break;
            case 1:
                result = cliente.getDireccion().toString();
                break;
            case 2:
                result = cliente.getEmail();
                break;
            case 3:
                result = cliente.getFechaAlta().toString();
                break;
            case 4:
                result = cliente.getTarifa().toString();
                break;
            default:
                result = null;
        }
        return result;
    }


    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }



    public void addTableModelListener(TableModelListener tml) {
        if (! tableModelListenerList.contains(tml)) {
            tableModelListenerList.add(tml);
        }
    }

    public void removeTableModelListener(TableModelListener tml) {
        tableModelListenerList.remove(tml);
    }

}
