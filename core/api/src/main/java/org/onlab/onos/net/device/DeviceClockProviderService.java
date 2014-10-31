/*
 * Copyright 2014 Open Networking Laboratory
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
package org.onlab.onos.net.device;

import org.onlab.onos.mastership.MastershipTerm;
import org.onlab.onos.net.DeviceId;

/**
* Interface for feeding term information to a logical clock service
* that vends per device timestamps.
*/
public interface DeviceClockProviderService {

    /**
     * Checks if this service can issue Timestamp for specified device.
     *
     * @param deviceId device identifier.
     * @return true if timestamp can be issued for specified device
     */
    public boolean isTimestampAvailable(DeviceId deviceId);

    /**
     * Updates the mastership term for the specified deviceId.
     *
     * @param deviceId device identifier.
     * @param term mastership term.
     */
    public void setMastershipTerm(DeviceId deviceId, MastershipTerm term);
}
