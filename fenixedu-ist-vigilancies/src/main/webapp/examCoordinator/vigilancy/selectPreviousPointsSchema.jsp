<%--

    Copyright © 2013 Instituto Superior Técnico

    This file is part of FenixEdu IST Vigilancies.

    FenixEdu IST Vigilancies is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu IST Vigilancies is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu IST Vigilancies.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html:xhtml/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="date"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/fenix-renderers" prefix="fr" %>


<h2><bean:message bundle="VIGILANCY_RESOURCES" key="label.vigilancy.selectPreviousPointsSchema"/></h2>

<ul>
	<li><a href="<%= request.getContextPath() + "/examCoordination/vigilancy/vigilantGroupManagement.do?method=prepareGroupPointsPropertyEdition&oid=" + request.getParameter("oid") %>"><bean:message bundle="VIGILANCY_RESOURCES" key="label.vigilancy.back"/></a></li>
</ul>

<fr:form action="<%= "/vigilancy/vigilantGroupManagement.do?method=changeYearForPoints&oid=" + request.getParameter("oid") %>">
	<fr:edit id="selectVigilantGroup" name="bean" schema="selectVigilantGroupForPoints">
		<fr:layout>
			<fr:property name="classes" value="tstyle5 thlight thright"/>
		</fr:layout>
	</fr:edit>
</fr:form>

<logic:present name="group">
	<bean:define id="groupID" name="group" property="externalId"/>
	<fr:form action="<%= "/vigilancy/vigilantGroupManagement.do?method=copySchemaPoints&show=groups&oid=" + request.getParameter("oid") + "&selectedGroupID=" + groupID %>">
		<fr:view name="group" schema="previousPointsForGroup">
			<fr:layout name="tabular">
				<fr:property name="classes" value="tstyle5 thlight thright"/>
				<fr:property name="columnClasses" value=",,tdclear tderror1"/>
			</fr:layout>
		</fr:view>
		
		<html:submit><bean:message bundle="RENDERER_RESOURCES" key="renderers.form.submit.name"/></html:submit>
	</fr:form>
</logic:present>