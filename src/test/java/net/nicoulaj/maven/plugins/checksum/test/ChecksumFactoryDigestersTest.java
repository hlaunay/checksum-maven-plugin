/*
 * Copyright 2010 Julien Nicoulaud <julien.nicoulaud@gmail.com>
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
package net.nicoulaj.maven.plugins.checksum.test;

import net.nicoulaj.maven.plugins.checksum.digester.DigesterFactory;
import org.codehaus.plexus.digest.Digester;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Tests for each checksum algorithm supported by the {@link DigesterFactory} class.
 *
 * @author Julien Nicoulaud <julien.nicoulaud@gmail.com>
 * @see net.nicoulaj.maven.plugins.checksum.digester.DigesterFactory
 * @since 0.1
 */
@RunWith(Parameterized.class)
public class ChecksumFactoryDigestersTest
{
    /**
     * The algorithm for which the {@link net.nicoulaj.maven.plugins.checksum.digester.DigesterFactory} is tested.
     */
    private String algorithm;

    /**
     * Generate the list of arguments with which the test should be run.
     *
     * @return the list of supported algorithms.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> getTestParameters()
    {
        Object[][] data = new Object[][]{{"CRC32"}, {"MD2"}, {"MD5"}, {"SHA-1"}, {"SHA-256"}, {"SHA-384"}, {"SHA-512"}};
        return Arrays.asList(data);
    }

    /**
     * Build a new {@link ChecksumFactoryDigestersTest}.
     *
     * @param algorithm the target checksum algorithm to run the test for.
     */
    public ChecksumFactoryDigestersTest(String algorithm)
    {
        this.algorithm = algorithm;
    }

    /**
     * Assert the returned {@link org.codehaus.plexus.digest.Digester} object is not null.
     *
     * @throws NoSuchAlgorithmException should never happen.
     */
    @Test
    public void testDigesterIsNotNull() throws NoSuchAlgorithmException
    {
        Digester digester = DigesterFactory.getInstance().getDigester(algorithm);
        Assert.assertNotNull(digester);
    }

    /**
     * Assert the returned {@link org.codehaus.plexus.digest.Digester} object is a singleton.
     *
     * @throws NoSuchAlgorithmException should never happen.
     */
    @Test
    public void testDigesterIsSingleton() throws NoSuchAlgorithmException
    {
        Digester digester1 = DigesterFactory.getInstance().getDigester(algorithm);
        Digester digester2 = DigesterFactory.getInstance().getDigester(algorithm);
        Assert.assertEquals(digester1, digester2);
    }
}