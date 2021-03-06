/**
 * Copyright (C) 2010 Mark Wolfe <mark.wolfe@wolfe.id.au>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package au.id.wolfe.tribs.resources;

import au.id.wolfe.tribs.data.WorkLogReport;
import au.id.wolfe.tribs.service.WorkLogService;
import au.id.wolfe.tribs.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WorkLogResourceTest {

    @Mock
    private WorkLogService workLogService;

    @Test
    public void testGetUserProjectWorkLogsForPeriod() throws Exception {

        WorkLogResource workLogResource = getWorkLogResource();

        Date startDate = DateUtils.parseISO8601Date("2000-01-01");
        Date endDate = DateUtils.parseISO8601Date("2020-01-01");

        when(
                workLogService.getUserProjectWorkLogsForPeriod("markw", "STAR",
                        startDate, endDate)).thenReturn(
                new WorkLogReport());

        WorkLogReport workLogReport = workLogResource
                .getUserProjectWorkLogsForPeriod("markw", "STAR", "2000-01-01",
                        "2020-01-01");

        assertNotNull(workLogReport);
    }

    private WorkLogResource getWorkLogResource() {
        return new WorkLogResource(workLogService);
    }
}
