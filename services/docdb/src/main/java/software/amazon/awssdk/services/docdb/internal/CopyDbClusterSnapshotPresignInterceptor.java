/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package software.amazon.awssdk.services.docdb.internal;

import java.time.Clock;
import software.amazon.awssdk.annotations.SdkInternalApi;
import software.amazon.awssdk.annotations.SdkTestInternalApi;
import software.amazon.awssdk.http.SdkHttpFullRequest;
import software.amazon.awssdk.services.docdb.model.CopyDbClusterSnapshotRequest;
import software.amazon.awssdk.services.docdb.transform.CopyDbClusterSnapshotRequestMarshaller;


/**
 * Handler for pre-signing {@link CopyDbClusterSnapshotRequest}.
 */
@SdkInternalApi
public final class CopyDbClusterSnapshotPresignInterceptor extends RdsPresignInterceptor<CopyDbClusterSnapshotRequest> {

    public static final CopyDbClusterSnapshotRequestMarshaller MARSHALLER =
            new CopyDbClusterSnapshotRequestMarshaller(PROTOCOL_FACTORY);

    public CopyDbClusterSnapshotPresignInterceptor() {
        super(CopyDbClusterSnapshotRequest.class);
    }

    @SdkTestInternalApi
    CopyDbClusterSnapshotPresignInterceptor(Clock signingDateOverride) {
        super(CopyDbClusterSnapshotRequest.class, signingDateOverride);
    }

    @Override
    protected PresignableRequest adaptRequest(final CopyDbClusterSnapshotRequest originalRequest) {
        return new PresignableRequest() {
            @Override
            public String getSourceRegion() {
                return originalRequest.sourceRegion();
            }

            @Override
            public SdkHttpFullRequest marshall() {
                return MARSHALLER.marshall(originalRequest);
            }
        };
    }
}
