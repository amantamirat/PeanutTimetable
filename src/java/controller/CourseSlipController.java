package controller;

import model.CourseSlip;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.AcademicCalendar;
import model.ActiveBatch;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named("courseSlipController")
@SessionScoped
public class CourseSlipController extends AbstractController<CourseSlip> {

    @EJB
    private ejb.CourseSlipFacade ejbFacade;
    @EJB
    private ejb.AcademicCalendarFacade acf;

    private MenuModel menuModel;
    
    private ActiveBatch selectedActiveBatch;

    public CourseSlipController() {
        super(CourseSlip.class);
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        menuModel = new DefaultMenuModel();
        DefaultSubMenu submenu;
        DefaultMenuItem item;

        for (AcademicCalendar ac : acf.findAll()) {
            submenu = DefaultSubMenu.builder()
                    .label(ac.toString())
                    .build();            
            for (ActiveBatch ab : ac.getActiveBatchCollection()) {                
                item = DefaultMenuItem.builder()
                        .value(ab)
                        .icon(ab.getAcademicCalendar().getSemester().getIcon())
                        .command("#{courseSlipController.onBatchSelect}")
                        .update(":CourseSlipListForm:datalist")
                        .ajax(false)
                        .build();
                submenu.getElements().add(item);
            }
            menuModel.getElements().add(submenu);
        }

    }   
    
    public void onBatchSelect(MenuActionEvent event){
      this.selectedActiveBatch = (ActiveBatch)event.getMenuItem().getValue();
    }

    public ActiveBatch getSelectedActiveBatch() {
        return selectedActiveBatch;
    }

    public void setSelectedActiveBatch(ActiveBatch selectedActiveBatch) {
        this.selectedActiveBatch = selectedActiveBatch;
    }    

    public CourseSlip getCourseSlip(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<CourseSlip> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<CourseSlip> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = CourseSlip.class)
    public static class CourseSlipControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CourseSlipController controller = (CourseSlipController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "courseSlipController");
            return controller.getCourseSlip(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CourseSlip) {
                CourseSlip o = (CourseSlip) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), CourseSlip.class.getName()});
                return null;
            }
        }

    }

}
