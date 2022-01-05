package controller;

import controller.util.JsfUtil;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.TimePeriodConf;
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
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

@Named("timePeriodConfController")
@SessionScoped
public class TimePeriodConfController extends AbstractController<TimePeriodConf> {

    @EJB
    private ejb.TimePeriodConfFacade ejbFacade;

    private List<DayOfWeek> selectedDays;

    private List<SortMeta> sortBy;

    public TimePeriodConfController() {
        super(TimePeriodConf.class);
    }

    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        sortBy = new ArrayList<>();
        sortBy.add(SortMeta.builder()
                .field("dayOfWeek")
                .order(SortOrder.ASCENDING)
                .priority(1)
                .build());

        sortBy.add(SortMeta.builder()
                .field("slotPeriod")
                .order(SortOrder.ASCENDING)
                .build());
    }

    @Override
    protected void setEmbeddableKeys() {
        if (selectedDays != null) {
            getSelected().setSelectedDays(TimePeriodConf.convertToDaysFlag(selectedDays));
        }
    }

    @Override
    protected void initializeEmbeddableKey() {
        super.initializeEmbeddableKey();
        getSelected().setSelectedDays("1111100");
        getSelected().setSlotDuration(50);
        getSelected().setSlotInterval(10);
        getSelected().setMinStartTime(LocalTime.of(2, 30));
        getSelected().setMaxEndTime(LocalTime.of(11, 30));
        getSelected().setTimeZone(0);
    }

    public List<DayOfWeek> getSelectedDays() {
        if (getSelected() != null) {
            this.selectedDays = TimePeriodConf.convertToSelectedDays(getSelected().getSelectedDays());
        }
        return selectedDays;
    }

    public void setSelectedDays(List<DayOfWeek> selectedDays) {
        this.selectedDays = selectedDays;
    }

    public List<SortMeta> getSortBy() {
        return sortBy;
    }

    public void remove(TimePeriodConf item) {
        setSelected(item);
        super.destroy();
    }

    public void updateDefault(TimePeriodConf item) {
        ejbFacade.updateDefault(item);
        setItems(null);
        JsfUtil.addSuccessMessage(null, item.toString() + (item.isDefaultConfiguration() ? " marked as default" : " marked as non-default"));
    }

    @FacesConverter(forClass = TimePeriodConf.class)
    public static class TimePeriodConfControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TimePeriodConfController controller = (TimePeriodConfController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "timePeriodConfController");
            return controller.getItem(getKey(value));
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
            if (object instanceof TimePeriodConf) {
                TimePeriodConf o = (TimePeriodConf) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TimePeriodConf.class.getName()});
                return null;
            }
        }

    }

}
