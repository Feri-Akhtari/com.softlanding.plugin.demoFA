/*
* © 2010-2019 COPYRIGHT UNICOM SYSTEMS, INC.
*
* ALL RIGHTS RESERVED
*
* THE INFORMATION CONTAINED HEREIN CONSTITUTES AN UNPUBLISHED
* WORK OF UNICOM SYSTEMS, INC. ALL RIGHTS RESERVED.
* NO MATERIAL FROM THIS WORK MAY BE REPRINTED,
* COPIED, OR EXTRACTED WITHOUT WRITTEN PERMISSION OF
* UNICOM SYSTEMS, INC.
*
*/

package com.softlanding.plugin.demoFA.views;

import java.util.Iterator;

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.softlanding.plugin.demoFA.dao.IDemoFADAO;
import com.softlanding.plugin.demoFA.products.Colour;
import com.softlanding.plugin.demoFA.dao.DemoFADAO;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Menu;

/**
 * 
 * @author FXA
 *
 */
public class DemoFAView extends ViewPart {
  public static final String ID = "com.gsk.spoolfilelist.SpooledView1";

  @Inject
  IWorkbench workbench;

  private TableViewer viewer;
  private IDemoFADAO connect = new DemoFADAO();
  private Action delete;
  private Action doubleClickAction;

  /**
   * @param parent Composite
   */
  public void createPartControl(Composite parent) {
    GridLayout layout = new GridLayout(2, false);
    parent.setLayout(layout);

    viewer = new TableViewer(parent,
        SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
    setColumns(parent, viewer);
    Table table = viewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);

    viewer.setContentProvider(new ArrayContentProvider());
    viewer.setInput(connect.getColours());

    // make the selection available to other views
    getSite().setSelectionProvider(viewer);

    // define layout for the viewer
    GridData gridData = new GridData();
    gridData.verticalAlignment = GridData.FILL;
    gridData.horizontalSpan = 2;
    gridData.grabExcessHorizontalSpace = true;
    gridData.grabExcessVerticalSpace = true;
    gridData.horizontalAlignment = GridData.FILL;
    viewer.getControl().setLayoutData(gridData);

    // Create the help context id for the viewer's control
    workbench.getHelpSystem().setHelp(viewer.getControl(), "com.gsk.spoolfilelist.viewer");
    getSite().setSelectionProvider(viewer);
    makeActions();
    hookContextMenu();
    hookDoubleClickAction();
    contributeToActionBars();
  }

  /**
   * 
   * @return Viewer
   */
  public TableViewer getViewer() {
    return viewer;
  }

  /**
   * creates the table columns, headers, sets the size of the columns and makes
   * the columns re-sizable.
   * 
   * @param parent  Parent
   * @param viewer1 Viewer
   */
  private void setColumns(Composite parent, TableViewer viewer1) {
    String[] titles = { "Status", "Colour Code", "Short Description", "Long Desciption" };
    int[] bounds = { 55, 95, 130, 220 };

    // Get the status
    TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
    col.setLabelProvider(new ColumnLabelProvider() {
      public String getText(Object object) {
        Colour colour = (Colour) object;
        return colour.getStatus();
      }
    });

    // Get the colour code
    col = createTableViewerColumn(titles[1], bounds[1], 1);
    col.setLabelProvider(new ColumnLabelProvider() {
      public String getText(Object object) {
        Colour colour = (Colour) object;
        return colour.getCode();
      }
    });

    // Get the short description
    col = createTableViewerColumn(titles[2], bounds[2], 2);
    col.setLabelProvider(new ColumnLabelProvider() {
      public String getText(Object object) {
        Colour colour = (Colour) object;
        return colour.getShortDescription();
      }
    });

    // Get the long description
    col = createTableViewerColumn(titles[2], bounds[3], 3);
    col.setLabelProvider(new ColumnLabelProvider() {
      public String getText(Object object) {
        Colour colour = (Colour) object;
        return colour.getLongDescription();
      }
    });

  }

  /**
   * 
   * @param title     Title
   * @param bound     Bound
   * @param colNumber Column Number
   * @return TableViewerColumn
   */
  private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
    final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
    final TableColumn column = viewerColumn.getColumn();
    column.setText(title);
    column.setWidth(bound);
    column.setResizable(true);
    column.setMoveable(true);
    return viewerColumn;
  }

  /**
   * 
   */
  private void hookContextMenu() {
    MenuManager menuMgr = new MenuManager("#PopupMenu");
    menuMgr.setRemoveAllWhenShown(true);
    menuMgr.addMenuListener(new IMenuListener() {
      public void menuAboutToShow(IMenuManager manager) {
        DemoFAView.this.fillContextMenu(manager);
      }
    });
    Menu menu = menuMgr.createContextMenu(viewer.getControl());
    viewer.getControl().setMenu(menu);
    getSite().registerContextMenu(menuMgr, viewer);
  }

  /**
   * 
   */
  private void contributeToActionBars() {
    IActionBars bars = getViewSite().getActionBars();
    fillLocalPullDown(bars.getMenuManager());
    fillLocalToolBar(bars.getToolBarManager());
  }

  /**
   * 
   * @param manager Manager
   */
  private void fillLocalPullDown(IMenuManager manager) {
    manager.add(delete);
    manager.add(new Separator());
  }

  /**
   * 
   * @param manager Manager
   */
  private void fillContextMenu(IMenuManager manager) {
    manager.add(delete);
    // Other plug-ins can contribute there actions here
    manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
  }

  /**
   * 
   * @param manager Manager
   */
  private void fillLocalToolBar(IToolBarManager manager) {
    manager.add(delete);
  }

  /**
   * 
   */
  private void makeActions() {
    delete = new Action() {
      public void run() {

        IStructuredSelection iss = viewer.getStructuredSelection();
        if (showMessageWarning("Press Ok to delete or Cancel") == 0) {
          @SuppressWarnings("unchecked")
          Iterator<Colour> iter = iss.iterator();
          while (iter.hasNext()) {
            connect.delete(((Colour) iter.next()).getCode());
          }
        }
        viewer.refresh();
        viewer.setInput(connect.getColours());
      }
    };
    delete.setText("Delete");
    delete.setToolTipText("Delete tooltip");
    delete.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
        .getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
    doubleClickAction = new Action() {
      public void run() {
        IStructuredSelection selection = viewer.getStructuredSelection();
        Colour c = (Colour) selection.getFirstElement();
        showMessageInfo("Selected colour details are:  " + c.toString());
      }
    };
  }

  /**
   * 
   */
  private void hookDoubleClickAction() {
    viewer.addDoubleClickListener(new IDoubleClickListener() {
      public void doubleClick(DoubleClickEvent event) {
        doubleClickAction.run();
      }
    });
  }

  /**
   * 
   * @param message Message
   */
  private void showMessageInfo(String message) {
    // MessageDialog.openInformation(viewer.getControl().getShell(), "Colour View",
    // message);
    // MessageDialogWithToggle.openInformation(viewer.getControl().getShell(),
    // "Colour View", message);
    MessageDialog dialog = new MessageDialog(viewer.getControl().getShell(), "Colour View", null,
        message, MessageDialog.INFORMATION, new String[] { "Ok"}, -1);
    dialog.open();
  }

  /**
   * 
   * @param message Message
   * @return int
   */
  private int showMessageWarning(String message) {
    // MessageDialog.openInformation(viewer.getControl().getShell(), "Colour View",
    // message);
    // MessageDialogWithToggle.openInformation(viewer.getControl().getShell(),
    // "Colour View", message);
    MessageDialog dialog = new MessageDialog(viewer.getControl().getShell(), "Colour View", null,
        message, MessageDialog.WARNING, new String[] { "Ok", "Cancel" }, -1);
    int result = dialog.open();
    return result;
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

}