package org.openstreetmap.atlas.checks.validation.intersections;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openstreetmap.atlas.checks.configuration.ConfigurationResolver;
import org.openstreetmap.atlas.checks.validation.verifier.ConsumerBasedExpectedCheckVerifier;

/**
 * {@link SelfIntersectingPolylineCheck} unit test
 *
 * @author mgostintsev
 */
public class SelfIntersectingPolyLineCheckTest
{
    private static final SelfIntersectingPolylineCheck check = new SelfIntersectingPolylineCheck(
            ConfigurationResolver.emptyConfiguration());

    @Rule
    public SelfIntersectingPolylineTestCaseRule setup = new SelfIntersectingPolylineTestCaseRule();

    @Rule
    public ConsumerBasedExpectedCheckVerifier verifier = new ConsumerBasedExpectedCheckVerifier();

    @Test
    public void testCheck()
    {
        this.verifier.actual(this.setup.getAtlas(), check);
        this.verifier.verifyExpectedSize(6);
        this.verifier.verify(flag ->
        {
            Assert.assertTrue(
                    this.setup.getInvalidAtlasEntityIdentifiers().contains(flag.getIdentifier()));
            Assert.assertFalse(
                    this.setup.getValidAtlasEntityIdentifiers().contains(flag.getIdentifier()));
        });
    }

}
