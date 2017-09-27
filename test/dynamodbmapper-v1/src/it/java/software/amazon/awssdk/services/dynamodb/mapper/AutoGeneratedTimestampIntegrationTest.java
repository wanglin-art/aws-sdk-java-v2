/*
 * Copyright 2010-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
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

package software.amazon.awssdk.services.dynamodb.mapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbAutoGenerateStrategy;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbAutoGeneratedTimestamp;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbHashKey;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbMapperConfig.SaveBehavior;
import software.amazon.awssdk.services.dynamodb.datamodeling.DynamoDbTable;
import software.amazon.awssdk.services.dynamodb.pojos.AutoKeyAndVal;
import software.amazon.awssdk.services.dynamodb.pojos.KeyAndVal;

/**
 * Tests updating component attribute fields correctly.
 */
public class AutoGeneratedTimestampIntegrationTest extends AbstractKeyAndValIntegrationTestCase {

    /**
     * Test using {@code Calendar}.
     */
    @Test
    public void testCalendarType() {
        final KeyAndCalendarTimestamp object = new KeyAndCalendarTimestamp();
        assertBeforeAndAfterChange(true, object);
    }

    /**
     * Test using {@code Date}.
     */
    @Test
    public void testDateType() {
        final KeyAndDateTimestamp object = new KeyAndDateTimestamp();

        assertBeforeAndAfterChange(true, object);
    }

    /**
     * Test using a {@code Long}.
     */
    @Test
    public void testLongType() {
        final KeyAndLongTimestamp object = new KeyAndLongTimestamp();

        assertBeforeAndAfterChange(true, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code ALWAYS}.
     */
    @Test
    public void testAlwaysStrategy() {
        final KeyAndDateTimestamp object = new KeyAndDateTimestamp();

        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code ALWAYS}.
     */
    @Test
    public void testAlwaysStrategyUpdateSkipNullAttribute() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final KeyAndDateTimestamp object = new KeyAndDateTimestamp();

        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code CREATE}.
     */
    @Test
    public void testCreateStrategy() {
        final KeyAndOnCreateDateTimestamp object = new KeyAndOnCreateDateTimestamp();

        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(false, object);
        assertBeforeAndAfterChange(false, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code CREATE}.
     */
    @Test
    public void testCreateStrategyUpdateSkipNullAttributes() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final KeyAndOnCreateDateTimestamp object = new KeyAndOnCreateDateTimestamp();

        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(false, object);
        assertBeforeAndAfterChange(false, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code ALWAYS}.
     */
    @Test
    public void testAlwaysNoKey() {
        final NoKeyAndOnAlwaysDateTimestamp object = new NoKeyAndOnAlwaysDateTimestamp();
        object.setKey(UUID.randomUUID().toString());

        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code ALWAYS}.
     */
    @Test
    public void testAlwaysNoKeyUpdateSkipNullAttributes() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final NoKeyAndOnAlwaysDateTimestamp object = new NoKeyAndOnAlwaysDateTimestamp();
        object.setKey(UUID.randomUUID().toString());

        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(true, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code CREATE}.
     */
    @Test
    public void testCreateNoKey() {
        final NoKeyAndOnCreateDateTimestamp object = new NoKeyAndOnCreateDateTimestamp();
        object.setKey(UUID.randomUUID().toString());

        assertBeforeAndAfterChange(true, object);
        assertBeforeAndAfterChange(false, object);
        assertBeforeAndAfterChange(false, object);
    }

    /**
     * Test {@code DynamoDBAutoGenerateStrategy} of {@code CREATE}.
     */
    @Test
    public void testCreateNoKeyUpdateSkipNullAttributes() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final NoKeyAndOnCreateDateTimestamp object = new NoKeyAndOnCreateDateTimestamp();
        object.setKey(UUID.randomUUID().toString());

        assertBeforeAndAfterChange(false, object);
        assertBeforeAndAfterChange(false, object);
        assertBeforeAndAfterChange(false, object);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testAlwaysOnBatchSave() {
        final List<KeyAndDateTimestamp> objects = new ArrayList<KeyAndDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            objects.add(new KeyAndDateTimestamp());
        }

        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testAlwaysOnBatchSaveUpdateSkipNullAttributes() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final List<KeyAndDateTimestamp> objects = new ArrayList<KeyAndDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            objects.add(new KeyAndDateTimestamp());
        }

        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testCreateOnBatchSave() {
        final List<KeyAndOnCreateDateTimestamp> objects = new ArrayList<KeyAndOnCreateDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            objects.add(new KeyAndOnCreateDateTimestamp());
        }

        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(false, objects);
        assertBeforeAndAfterChange(false, objects);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testCreateOnBatchSaveUpdateSkipNullAttributes() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final List<KeyAndOnCreateDateTimestamp> objects = new ArrayList<KeyAndOnCreateDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            objects.add(new KeyAndOnCreateDateTimestamp());
        }

        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(false, objects);
        assertBeforeAndAfterChange(false, objects);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testAlwaysNoKeyOnBatchSave() {
        final List<NoKeyAndOnAlwaysDateTimestamp> objects = new ArrayList<NoKeyAndOnAlwaysDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            final NoKeyAndOnAlwaysDateTimestamp object = new NoKeyAndOnAlwaysDateTimestamp();
            object.setKey(UUID.randomUUID().toString());
            objects.add(object);
        }

        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testAlwaysNoKeyOnBatchSaveUpdateSkipNullAttributes() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final List<NoKeyAndOnAlwaysDateTimestamp> objects = new ArrayList<NoKeyAndOnAlwaysDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            final NoKeyAndOnAlwaysDateTimestamp object = new NoKeyAndOnAlwaysDateTimestamp();
            object.setKey(UUID.randomUUID().toString());
            objects.add(object);
        }

        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(true, objects);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testCreateNoKeyOnBatchSave() {
        final List<NoKeyAndOnCreateDateTimestamp> objects = new ArrayList<NoKeyAndOnCreateDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            final NoKeyAndOnCreateDateTimestamp object = new NoKeyAndOnCreateDateTimestamp();
            object.setKey(UUID.randomUUID().toString());
            objects.add(object);
        }

        assertBeforeAndAfterChange(true, objects);
        assertBeforeAndAfterChange(false, objects);
        assertBeforeAndAfterChange(false, objects);
    }

    /**
     * Test the batch save.
     */
    @Test
    public void testCreateNoKeyOnBatchSaveUpdateSkipNullAttributes() {
        setUpTest(SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES);

        final List<NoKeyAndOnCreateDateTimestamp> objects = new ArrayList<NoKeyAndOnCreateDateTimestamp>();
        for (int i = 0; i < 10; i++) {
            final NoKeyAndOnCreateDateTimestamp object = new NoKeyAndOnCreateDateTimestamp();
            object.setKey(UUID.randomUUID().toString());
            objects.add(object);
        }

        assertBeforeAndAfterChange(false, objects);
        assertBeforeAndAfterChange(false, objects);
        assertBeforeAndAfterChange(false, objects);
    }

    /**
     * An object with {@code Calendar}.
     */
    @DynamoDbTable(tableName = "aws-java-sdk-util")
    public static class KeyAndCalendarTimestamp extends AutoKeyAndVal<Calendar> {
        @DynamoDbAutoGeneratedTimestamp
        public Calendar getVal() {
            return super.getVal();
        }

        public void setVal(final Calendar val) {
            super.setVal(val);
        }
    }

    /**
     * An object with {@code Date}.
     */
    @DynamoDbTable(tableName = "aws-java-sdk-util")
    public static class KeyAndDateTimestamp extends AutoKeyAndVal<Date> {
        @DynamoDbAutoGeneratedTimestamp
        public Date getVal() {
            return super.getVal();
        }

        public void setVal(final Date val) {
            super.setVal(val);
        }
    }

    /**
     * An object with {@code Long}.
     */
    @DynamoDbTable(tableName = "aws-java-sdk-util")
    public static class KeyAndLongTimestamp extends AutoKeyAndVal<Long> {
        @DynamoDbAutoGeneratedTimestamp
        public Long getVal() {
            return super.getVal();
        }

        public void setVal(final Long val) {
            super.setVal(val);
        }
    }

    /**
     * An object with {@code Date} only on {@code CREATE}.
     */
    @DynamoDbTable(tableName = "aws-java-sdk-util")
    public static class KeyAndOnCreateDateTimestamp extends AutoKeyAndVal<Date> {
        @DynamoDbAutoGeneratedTimestamp(strategy = DynamoDbAutoGenerateStrategy.CREATE)
        public Date getVal() {
            return super.getVal();
        }

        public void setVal(final Date val) {
            super.setVal(val);
        }
    }

    /**
     * An object with {@code Date} not auto-generted key.
     */
    @DynamoDbTable(tableName = "aws-java-sdk-util")
    public static class NoKeyAndOnAlwaysDateTimestamp extends KeyAndVal<String, Date> {
        @DynamoDbHashKey
        public String getKey() {
            return super.getKey();
        }

        public void setKey(final String key) {
            super.setKey(key);
        }

        @DynamoDbAutoGeneratedTimestamp
        public Date getVal() {
            return super.getVal();
        }

        public void setVal(final Date val) {
            super.setVal(val);
        }
    }

    /**
     * An object with {@code Date} not auto-generted key.
     */
    @DynamoDbTable(tableName = "aws-java-sdk-util")
    public static class NoKeyAndOnCreateDateTimestamp extends KeyAndVal<String, Date> {
        @DynamoDbHashKey
        public String getKey() {
            return super.getKey();
        }

        public void setKey(final String key) {
            super.setKey(key);
        }

        @DynamoDbAutoGeneratedTimestamp(strategy = DynamoDbAutoGenerateStrategy.CREATE)
        public Date getVal() {
            return super.getVal();
        }

        public void setVal(final Date val) {
            super.setVal(val);
        }
    }

}