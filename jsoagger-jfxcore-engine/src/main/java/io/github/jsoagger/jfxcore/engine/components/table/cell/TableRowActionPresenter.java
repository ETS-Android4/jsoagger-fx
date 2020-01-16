/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package io.github.jsoagger.jfxcore.engine.components.table.cell;



import io.github.jsoagger.jfxcore.engine.client.components.ComponentToButtonBaseHelper;
import io.github.jsoagger.jfxcore.engine.client.utils.NodeHelper;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.utils.StringUtils;
import io.github.jsoagger.jfxcore.api.IJSoaggerController;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;
import io.github.jsoagger.jfxcore.engine.components.table.row.EpTableRow;
import io.github.jsoagger.jfxcore.engine.components.tablestructure.CellPresenterImpl;
import io.github.jsoagger.jfxcore.engine.controller.AbstractViewController;
import io.github.jsoagger.jfxcore.engine.utils.IconUtils;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 13 févr. 2018
 */
public class TableRowActionPresenter extends CellPresenterImpl {

  private final Hyperlink label = new Hyperlink();



  /**
   * Default Constructor
   */
  public TableRowActionPresenter() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public Node present(IJSoaggerController controller, VLViewComponentXML configuration, Object item) {
    String rowCriteria = configuration.getPropertyValue("rowCriteria");
    boolean show = true;
    if(StringUtils.isNotBlank(rowCriteria)) {
      EpTableCell c = (EpTableCell) cell;
      EpTableRow r = (EpTableRow) c.getTableRow();
      show = r.evaluateCriteria(rowCriteria);
    }

    if(show) {
      IconUtils.setIcon(label, configuration);
      if (label.getGraphic() != null) {
        label.getGraphic().getStyleClass().add("grey-flat-ikonli");
      }

      NodeHelper.setTitle(label, configuration, (AbstractViewController) controller);
      NodeHelper.setStyleClass(label, configuration, "buttonStyleClass", true);
      ComponentToButtonBaseHelper.setOnAction(configuration, label, (AbstractViewController) controller, (OperationData) item);
    } else {
      label.setManaged(false);
      label.setVisible(false);
    }

    return label;
  }
}
