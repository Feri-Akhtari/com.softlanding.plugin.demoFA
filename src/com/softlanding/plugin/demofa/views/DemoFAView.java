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

package com.softlanding.plugin.demofa.views;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import org.eclipse.swt.graphics.Image;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;

import org.eclipse.swt.widgets.Menu;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.SWT;

import java.util.Iterator;
import java.util.ListIterator;

import javax.inject.Inject;


import com.softlanding.plugin.demofa.dao.DemoFADAO;
import com.softlanding.plugin.demofa.dao.DemoFAHardCode;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class DemoFAView extends ViewPart {

  /**
   * The ID of the view as specified by the extension.
   */
  public static final String ID = "com.softlanding.plugin.demofa.views.DemoFAView";

  @Inject
  private IWorkbench workbench;

  private TableViewer viewer;
  private Action delete;
  private Action doubleClickAction;

  /**
   * 
   * @author FXA
   *
   */
  class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
    @Override
    public String getColumnText(Object obj, int index) {
      return getText(obj);
    }

    @Override
    public Image getColumnImage(Object obj, int index) {
      return getImage(obj);
    }

    @Override
    public Image getImage(Object obj) {
      return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
    }
  }

  @Override
  public void createPartControl(Composite parent) {
    viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    viewer.setContentProvider(ArrayContentProvider.getInstance());
//    DemoFADAO connect = new DemoFADAO();
    DemoFAHardCode connect = new DemoFAHardCode();
    viewer.setInput(connect.getRecords());
//    Table table = viewer.getTable();
//    new TableColumn(table, SWT.LEFT).setText("First Name");
//    new TableColumn(table, SWT.LEFT).setText("Last Name");
//    new TableColumn(table, SWT.LEFT).setText("E-mail Address");
//    table.setHeaderVisible(true);
//    table.setLinesVisible(true);
    viewer.setLabelProvider(new ViewLabelProvider());

    // Create the help context id for the viewer's control
    workbench.getHelpSystem().setHelp(viewer.getControl(), "com.softlanding.plugin.demoFA.viewer");
    getSite().setSelectionProvider(viewer);
    makeActions();
    hookContextMenu();
    hookDoubleClickAction();
    contributeToActionBars();
    // headerColumn.substring(start, end)
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
   * @param manager manager.
   */
  private void fillLocalPullDown(IMenuManager manager) {
    delete.setText("Delete");
    manager.add(delete);
    manager.add(new Separator());
  }

  /**
   * 
   * @param manager manager.
   */
  private void fillContextMenu(IMenuManager manager) {
    manager.add(delete);
    // Other plug-ins can contribute there actions here
    manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
  }

  /**
   * 
   * @param manager manager.
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
        showMessage("Record deleted");
        ISelection is = viewer.getSelection();
        String d = is.toString();
        IStructuredSelection iss = viewer.getStructuredSelection();
        d = iss.iterator().toString();
        //DemoFADAO connect = new DemoFADAO();
        DemoFAHardCode connect = new DemoFAHardCode();
        //
        Iterator<?> selectedElements = ((IStructuredSelection)is).iterator();
//      Object selectedElement = selectedElements.next();
//      ArrayList<Object> selectionList = new ArrayList<Object> ();
//      selectionList.add(selectedElement);
      while (selectedElements.hasNext()) {
        d = selectedElements.next().toString().substring(2, 6);
        connect.delete(d);
      } 
      Iterator iter = iss.iterator();
      while (iter.hasNext()) {
        String obj1 = ((String)iter.next()).substring(2, 6);
      }
//      ListIterator<String> iter = list.iterator();
//      while (iter.hasNext()) {
//        String str = iter.next();
//      }
      
        //

        viewer.refresh();
        viewer.setInput(connect.getRecords());
      }
    };
    delete.setText("Delete");
    delete.setToolTipText("Delete tooltip");
    delete.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
        .getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
    doubleClickAction = new Action() {
      public void run() {
        IStructuredSelection selection = viewer.getStructuredSelection();
        Object obj = selection.getFirstElement();
        showMessage("Double-click detected on " + obj.toString());
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
   * @param message message,
   */
  private void showMessage(String message) {
    MessageDialog.openInformation(viewer.getControl().getShell(), "DemoFA View", message);
  }

  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }

}
