/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import javax.faces.convert.FacesConverter;
import model.Batch;

/**
 *
 * @author Amanuel
 */
@FacesConverter(value = "batchConverter", forClass = Batch.class)
public class BatchConverter extends AbstractConverter<Batch> {

}
