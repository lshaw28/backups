<%@ include file="/apps/searspartsdirect/global.jsp" %><%
    %><%@ page import="com.day.cq.commons.jcr.JcrConstants" %><%
    %><spd:uniqueID /><%
    %>
<div class="row-fluid">
    <div class="span12">
        <h1>What are MERV ratings for air filters? <br /> What should I buy?</h1>
    </div>
</div>
<div class="row-fluid hidden-phone">
    <div class="span12 border1px">
        <h3>What is a MERV rating?</h3>
        <p>MERV stands for Mimium Efficiency Rating Value.It's an industry standard rating system, provided by <a href="/content/searspartsdirect/en/air-filter-dimensions.html">air filter manufacturers</a>,
            designed to help choose which efficiency works best for your household. A MERV rating is a numerical value
            ranging from 1 (lowest efficiency) to 16 (highest efficiency) and tells the consumer how well the filter captures and holds dirt and dust.
        </p>
    </div>
    <div class="span12">
        <h3>What are the MERV ratings of the filters carried by Sears PartsDirect?</h3>
    </div>
    <div class="span12">
        <div class="aitFilterTypeHeading">Best</div>
        <p>MERV ratings: 13-16</p>
        <p>
            The most efficient filtration, suitable for hospitals and healthcare facilities. Eliminates 95% or more airborne contaminants 0.3 microns or larger,
            ranging from bacteria to tobacco smoke to proplet nuclei (sneeze). Filter life is typically up to 90 days.
        </p>
        <p><strong>Percentage of particles trapped</strong><br />
            85% or better of particles 3.0 - 10.0 microns<br />
            90% or better of particles 1.0 - 3.0 microns</p>
        <p>MERV 13: Less than 75% of particles 0.3 - 1.0 microns<br />
            MERV 14: 75% - 84% of particles 0.3 - 1.0 microns<br />
            MERV 15: 85% - 94% of particles 0.3 - 1.0 microns<br />
            MERV 16: 95% or better of particles 0.3 - 1.0 microns
        </p>        
    </div>
    <div class="span12">
        <div class="aitFilterTypeHeading">Better</div>
        <p>MERV ratings: 9-12</p>
        <p>
            A very high level of filtration, good for homes prone to allergies or asthma. Removes up to 89% of airborne contaminants one micron or larger,
            ranging from humidifier and lead dust to auto emissions and milled flour. Filter life is typically up to 90 days.
        </p>
        <p><strong>Percentage of particles trapped</strong><br/>
            85% or better of particles 3.0 - 10.0 microns</p>
        <p>MERV 9: Less than 50% of particles 1.0 - 3.0 microns<br />
            MERV 10: 50% - 64% of particles 1.0 - 3.0 microns<br />
            MERV 11: 65% - 79% of particles 1.0 - 3.0 microns<br />
            MERV 12: 80% - 89% of particles 1.0 - 3.0 microns</p>
    </div>
    <div class="span12 border1pxBottom">
        <div class="aitFilterTypeHeading">Good</div>
        <p>MERV ratings: 5-8</p>
        <p>
            Efficient air filtration for homes on a budget, good for homes with minor allergy sufferers looking to eliminate 
            mold spores, hair spray, fabric protector or cement dust from the air.
            Removes up to 80% of airborne contaminants three microns or larger. Filter life is typically up to 90 days.
        </p>
        <p><strong>Percentage of particles trapped</strong><br/>
            MERV 5: 20% - 34% of particles 3.0 - 10.0 microns<br/>
            MERV 6: 35% - 49% of particles 3.0 - 10.0 microns<br/>
            MERV 7: 50% - 69% of particles 3.0 - 10.0 microns<br/>
            MERV 8: 70% - 80% of particles 3.0 - 10.0 microns</p>
    </div>
</div>

<!-- mobile code -->

<c:set var="uniquer" value="0" />

<div class="accordion first visible-phone border1px" id="parent_${uniqueId}${uniquer}">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#parent_${uniqueId}${uniquer}" href="#${uniqueId}${uniquer}">What is a MERV rating?<i class="icon-chevron-down dropDownArrow"></i></a>
        </div>
        <div id="${uniqueId}${uniquer}" class="accordion-body collapse">
            <div class="accordion-inner">
                MERV stands for Mimium Efficiency Rating Value.It's an industry standard rating system, provided by air filter manufacturers,
                designed to help choose which efficiency works best for your household. A MERV rating is a numerical value
                ranging from 1 (lowest efficiency) to 16 (highest efficiency) and tells the consumer how well the filter captures and holds dirt and dust.
            </div>
        </div>
    </div>
</div>
<c:set var="uniquer" value="${uniquer + 1}" />
<div class="accordion visible-phone" id="parent_${uniqueId}${uniquer}">
    <div class="accordion-group">
        <div class="accordion-heading">
            <a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#parent_${uniqueId}${uniquer}" href="#${uniqueId}${uniquer}">What are the MERV ratings of the filters carried by Sears PartsDirect?<i class="icon-chevron-down dropDownArrow">&nbsp;</i> </a>
        </div>
        <div id="${uniqueId}${uniquer}" class="accordion-body collapse">
            <div class="accordion-inner">
                <div class="span12">
                    <p><strong>Best</strong><br />
                        MERV ratings: 13-16</p>
                    <p>
                        The most efficient filtration, suitable for hospitals and healthcare facilities. Eliminates 95% or more airborne contaminants 0.3 microns or larger,
                        ranging from bacteria to tobacco smoke to proplet nuclei (sneeze). Filter life is typically up to 90 days.
                    </p>
                    <p><strong>Percentage of particles trapped</strong><br />
                        85% or better of particles 3.0 - 10.0 microns<br />
                        90% or better of particles 1.0 - 3.0 microns</p>
                    <p>MERV 13: Less than 75% of particles 0.3 - 1.0 microns<br />
                        MERV 14: 75% - 84% of particles 0.3 - 1.0 microns<br />
                        MERV 15: 85% - 94% of particles 0.3 - 1.0 microns<br />
                        MERV 16: 95% or better of particles 0.3 - 1.0 microns
                    </p>
                </div>	
                <div class="span12">
                    <p><strong>Better</strong><br />
                        MERV ratings: 9-12</p>
                    <p>
                        A very high level of filtration, good for homes prone to allergies or asthma. Removes up to 89% of airborne contaminants one micron or larger,
                        ranging from humidifier and lead dust to auto emissions and milled flour. Filter life is typically up to 90 days.
                    </p>
                    <p><strong>Percentage of particles trapped</strong><br/>
                        85% or better of particles 3.0 - 10.0 microns</p>
                    <p>MERV 9: Less than 50% of particles 1.0 - 3.0 microns<br />
                        MERV 10: 50% - 64% of particles 1.0 - 3.0 microns<br />
                        MERV 11: 65% - 79% of particles 1.0 - 3.0 microns<br />
                        MERV 12: 80% - 89% of particles 1.0 - 3.0 microns</p>
                </div>

                <div class="span12">
                    <p><strong>Good</strong><br />
                        MERV ratings: 5-8</p>
                    <p>
                        Efficient air filtration for homes on a budget, good for homes with minor allergy sufferers looking to eliminate 
                        mold spores, hair spray, fabric protector or cement dust from the air.
                        Removes up to 80% of airborne contaminants three microns or larger. Filter life is typically up to 90 days.
                    </p>
                    <p><strong>Percentage of particles trapped</strong></p>
                    <p>MERV 5: 20% - 34% of particles 3.0 - 10.0 microns<br/>
                        MERV 6: 35% - 49% of particles 3.0 - 10.0 microns<br/>
                        MERV 7: 50% - 69% of particles 3.0 - 10.0 microns<br/>
                        MERV 8: 70% - 80% of particles 3.0 - 10.0 microns</p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <a href="/content/searspartsdirect/en/air-filter-dimensions.html" class="shopAirFilterAnchor">Shop Air Filters</a>
    </div>
</div>