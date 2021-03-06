/*
 * Copyright 2017-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.net.intent.impl.installer;

import org.onosproject.TestApplicationId;
import org.onosproject.core.ApplicationId;
import org.onosproject.net.ConnectPoint;
import org.onosproject.net.FilteredConnectPoint;
import org.onosproject.net.ResourceGroup;
import org.onosproject.net.flow.DefaultTrafficSelector;
import org.onosproject.net.flow.DefaultTrafficTreatment;
import org.onosproject.net.flow.TrafficSelector;
import org.onosproject.net.flow.TrafficTreatment;
import org.onosproject.net.intent.AbstractIntentTest;
import org.onosproject.net.intent.IntentExtensionService;
import org.onosproject.net.intent.IntentInstallCoordinator;
import org.onosproject.net.intent.IntentOperationContext;
import org.onosproject.net.intent.Key;
import org.onosproject.net.intent.PointToPointIntent;
import org.onosproject.net.intent.ObjectiveTrackerService;

import static org.easymock.EasyMock.createMock;

/**
 * Abstract class to hold the common variables and pieces of code for Intent
 * installer test.
 */
public class AbstractIntentInstallerTest extends AbstractIntentTest {
    protected static final ApplicationId APP_ID = TestApplicationId.create("IntentInstallerTest");
    protected static final ConnectPoint CP1 = ConnectPoint.deviceConnectPoint("s1/1");
    protected static final ConnectPoint CP2 = ConnectPoint.deviceConnectPoint("s1/2");
    protected static final ConnectPoint CP3 = ConnectPoint.deviceConnectPoint("s1/3");
    protected static final Key KEY1 = Key.of("test intent 1", APP_ID);
    protected static final ResourceGroup RG1 = ResourceGroup.of("test resource group 1");
    protected static final int DEFAULT_PRIORITY = 30000;

    protected IntentExtensionService intentExtensionService;
    protected ObjectiveTrackerService trackerService;
    protected TestIntentInstallCoordinator intentInstallCoordinator;

    public void setup() {
        super.setUp();
        intentExtensionService = createMock(IntentExtensionService.class);
        trackerService = createMock(ObjectiveTrackerService.class);
        intentInstallCoordinator = new TestIntentInstallCoordinator();
    }

    /**
     * Creates point to point Intent for test.
     *
     * @return the point to point Intent
     */
    public PointToPointIntent createP2PIntent() {
        PointToPointIntent intent;
        TrafficSelector selector = DefaultTrafficSelector.emptySelector();
        TrafficTreatment treatment = DefaultTrafficTreatment.emptyTreatment();

        FilteredConnectPoint ingress = new FilteredConnectPoint(CP1);
        FilteredConnectPoint egress = new FilteredConnectPoint(CP2);

        intent = PointToPointIntent.builder()
                .selector(selector)
                .treatment(treatment)
                .filteredIngressPoint(ingress)
                .filteredEgressPoint(egress)
                .appId(APP_ID)
                .build();

        return intent;
    }

    /**
     * The Intent install coordinator for test.
     * Records success and fail context.
     */
    class TestIntentInstallCoordinator implements IntentInstallCoordinator {

        IntentOperationContext successContext;
        IntentOperationContext failedContext;

        @Override
        public void intentInstallSuccess(IntentOperationContext context) {
            successContext = context;
        }

        @Override
        public void intentInstallFailed(IntentOperationContext context) {
            failedContext = context;
        }
    }

}
