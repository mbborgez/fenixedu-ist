/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST Integration.
 *
 * FenixEdu IST Integration is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST Integration is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST Integration.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.integration.ui.struts.action.departmentAdmOffice;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.fenixedu.academic.domain.Department;
import org.fenixedu.academic.domain.Person;
import org.fenixedu.academic.domain.Teacher;
import org.fenixedu.academic.ui.struts.action.base.FenixAction;
import org.fenixedu.academic.util.Bundle;
import org.fenixedu.bennu.core.i18n.BundleUtil;
import org.fenixedu.bennu.struts.annotations.Mapping;
import org.fenixedu.bennu.struts.portal.StrutsFunctionality;

import pt.ist.fenixedu.integration.ui.struts.action.DepartmentAdmOfficeTeachersApp;
import pt.utl.ist.fenix.tools.util.excel.Spreadsheet;
import pt.utl.ist.fenix.tools.util.excel.Spreadsheet.Row;

@StrutsFunctionality(app = DepartmentAdmOfficeTeachersApp.class, path = "search", titleKey = "link.teachers.search")
@Mapping(module = "departmentAdmOffice", path = "/searchTeachers")
public class SearchTeachersDA extends FenixAction {

    private Department getDepartment(final HttpServletRequest request) {
        return getUserView(request).getPerson().getEmployee().getCurrentDepartmentWorkingPlace();
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm actionForm, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; filename=teachers.xls");

        final Department department = getDepartment(request);

        final Spreadsheet spreadsheet = getSpreadsheet();
        for (final Teacher teacher : department.getAllCurrentTeachers()) {
            final Person person = teacher.getPerson();

            final Row row = spreadsheet.addRow();
            row.setCell(teacher.getPerson().getUsername());
            row.setCell(person.getName());
            row.setCell(person.getEmail());
        }

        final OutputStream outputStream = response.getOutputStream();
        spreadsheet.exportToXLSSheet(outputStream);
        outputStream.close();
        return null;
    }

    private Spreadsheet getSpreadsheet() {
        final Spreadsheet spreadsheet = new Spreadsheet("Teachers");
        spreadsheet.setHeader("Identificação");
        spreadsheet.setHeader(BundleUtil.getString(Bundle.APPLICATION, "label.person.name"));
        spreadsheet.setHeader(BundleUtil.getString(Bundle.APPLICATION, "label.person.email"));
        return spreadsheet;
    }

}
