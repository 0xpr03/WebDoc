/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.lib;

import java.sql.Date;
import java.sql.Time;

/**
 * Builder pattern for Anamnesis inserts / edits
 * @author "Aron Heinecke"
 */
public class AnamnesisBP {
	public long getTierid() {
		return tierid;
	}

	public long getAnamnesisID() {
		return anamnesisID;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getKeeping() {
		return keeping;
	}

	public Date getPossesionsince() {
		return possesionsince;
	}

	public String getOrigin() {
		return origin;
	}

	public String getFamilystrchanges() {
		return familystrchanges;
	}

	public String getAbroadstays() {
		return abroadstays;
	}

	public String getAttitudeconspicuity() {
		return attitudeconspicuity;
	}

	public String getInjurys() {
		return injurys;
	}

	public String getScars() {
		return scars;
	}

	public String getInfectiousDisease() {
		return infectiousDisease;
	}

	public String getRegularVaccinations() {
		return regularVaccinations;
	}

	public String getBreathing() {
		return breathing;
	}

	public String getDigestiveTract() {
		return digestiveTract;
	}

	public String getEndocrineSystem() {
		return endocrineSystem;
	}

	public String getHyperthyroidism() {
		return hyperthyroidism;
	}

	public String getPancreas() {
		return pancreas;
	}

	public String getZNS() {
		return ZNS;
	}

	public int getEpileptiformAttacks() {
		return epileptiformAttacks;
	}

	public String getXray() {
		return xray;
	}

	public String getMedication() {
		return medication;
	}

	public String getCT_MRT() {
		return CT_MRT;
	}

	public String getMainproblem() {
		return mainproblem;
	}

	public String getDescrPatientOwner() {
		return descrPatientOwner;
	}

	public String getWasUndertaken() {
		return wasUndertaken;
	}

	public int getPainSensitivity() {
		return painSensitivity;
	}

	public int getPatientHasPain() {
		return patientHasPain;
	}

	public String getPainkillerReaction() {
		return painkillerReaction;
	}

	public String getMotionCausingPain() {
		return motionCausingPain;
	}

	public String getMotorInterference() {
		return motorInterference;
	}

	public int getBodyPartUsagePossible() {
		return bodyPartUsagePossible;
	}

	public double getPossibleWalkDistance() {
		return possibleWalkDistance;
	}

	public Time getPossibleWalkDuration() {
		return possibleWalkDuration;
	}

	public int getWeatherDependent() {
		return weatherDependent;
	}

	public int getCycleCorrelation() {
		return cycleCorrelation;
	}

	public double getOutlet() {
		return outlet;
	}

	public Time getAvailableTimeCons() {
		return availableTimeCons;
	}

	public String getComment() {
		return comment;
	}
	
	public String getCirculation() {
		return circulation;
	}

	private long tierid;
	private long anamnesisID;
	private String purpose;
	private String keeping;
	private Date possesionsince;
	private String origin;
	private String familystrchanges;
	private String abroadstays;
	private String attitudeconspicuity;
	private String injurys;
	private String scars;
	private String infectiousDisease;
	private String regularVaccinations;
	private String breathing;
	private String digestiveTract;
	private String endocrineSystem;
	private String hyperthyroidism;
	private String pancreas;
	private String ZNS;
	private String circulation;
	private int epileptiformAttacks;
	private String xray;
	private String medication;
	private String CT_MRT;
	private String mainproblem;
	private String descrPatientOwner;
	private String wasUndertaken;
	private int painSensitivity;
	private int patientHasPain;
	private String painkillerReaction;
	private String motionCausingPain;
	private String motorInterference;
	private int bodyPartUsagePossible;
	private double possibleWalkDistance;
	private Time possibleWalkDuration;
	private int weatherDependent;
	private int cycleCorrelation;
	private double outlet;
	private Time availableTimeCons;
	private String comment;

	public static class Builder {
		private long tierid;
		private long anamnesisID;
		private String purpose;
		private String keeping;
		private Date possesionsince;
		private String origin;
		private String familystrchanges;
		private String abroadstays;
		private String attitudeconspicuity;
		private String injurys;
		private String scars;
		private String infectiousDisease;
		private String regularVaccinations;
		private String breathing;
		private String digestiveTract;
		private String endocrineSystem;
		private String hyperthyroidism;
		private String pancreas;
		private String ZNS;
		private int epileptiformAttacks;
		private String xray;
		private String medication;
		private String CT_MRT;
		private String mainproblem;
		private String descrPatientOwner;
		private String wasUndertaken;
		private int painSensitivity;
		private int patientHasPain;
		private String painkillerReaction;
		private String motionCausingPain;
		private String motorInterference;
		private String circulation;
		private int bodyPartUsagePossible;
		private double possibleWalkDistance;
		private Time possibleWalkDuration;
		private int weatherDependent;
		private int cycleCorrelation;
		private double outlet;
		private Time availableTimeCons;
		private String comment;
		
		public Builder(long tierid){
			this.tierid = tierid;
		}

		public Builder tierid(long tierid) {
			this.tierid = tierid;
			return this;
		}

		public Builder anamnesisID(long anamnesisID) {
			this.anamnesisID = anamnesisID;
			return this;
		}
		
		public Builder circulation(String circulation) {
			this.circulation = circulation;
			return this;
		}

		public Builder purpose(String purpose) {
			this.purpose = purpose;
			return this;
		}

		public Builder keeping(String keeping) {
			this.keeping = keeping;
			return this;
		}

		public Builder possesionsince(Date possesionsince) {
			this.possesionsince = possesionsince;
			return this;
		}

		public Builder origin(String origin) {
			this.origin = origin;
			return this;
		}

		public Builder familystrchanges(String familystrchanges) {
			this.familystrchanges = familystrchanges;
			return this;
		}

		public Builder abroadstays(String abroadstays) {
			this.abroadstays = abroadstays;
			return this;
		}

		public Builder attitudeconspicuity(String attitudeconspicuity) {
			this.attitudeconspicuity = attitudeconspicuity;
			return this;
		}

		public Builder injurys(String injurys) {
			this.injurys = injurys;
			return this;
		}

		public Builder scars(String scars) {
			this.scars = scars;
			return this;
		}

		public Builder infectiousDisease(String infectiousDisease) {
			this.infectiousDisease = infectiousDisease;
			return this;
		}

		public Builder regularVaccinations(String regularVaccinations) {
			this.regularVaccinations = regularVaccinations;
			return this;
		}

		public Builder breathing(String breathing) {
			this.breathing = breathing;
			return this;
		}

		public Builder digestiveTract(String digestiveTract) {
			this.digestiveTract = digestiveTract;
			return this;
		}

		public Builder endocrineSystem(String endocrineSystem) {
			this.endocrineSystem = endocrineSystem;
			return this;
		}

		public Builder hyperthyroidism(String hyperthyroidism) {
			this.hyperthyroidism = hyperthyroidism;
			return this;
		}

		public Builder pancreas(String pancreas) {
			this.pancreas = pancreas;
			return this;
		}

		public Builder ZNS(String ZNS) {
			this.ZNS = ZNS;
			return this;
		}

		public Builder epileptiformAttacks(int epileptiformAttacks) {
			this.epileptiformAttacks = epileptiformAttacks;
			return this;
		}

		public Builder xray(String xray) {
			this.xray = xray;
			return this;
		}

		public Builder medication(String medication) {
			this.medication = medication;
			return this;
		}

		public Builder CT_MRT(String CT_MRT) {
			this.CT_MRT = CT_MRT;
			return this;
		}

		public Builder mainproblem(String mainproblem) {
			this.mainproblem = mainproblem;
			return this;
		}

		public Builder descrPatientOwner(String descrPatientOwner) {
			this.descrPatientOwner = descrPatientOwner;
			return this;
		}

		public Builder wasUndertaken(String wasUndertaken) {
			this.wasUndertaken = wasUndertaken;
			return this;
		}

		public Builder painSensitivity(int painSensitivity) {
			this.painSensitivity = painSensitivity;
			return this;
		}

		public Builder patientHasPain(int patientHasPain) {
			this.patientHasPain = patientHasPain;
			return this;
		}

		public Builder painkillerReaction(String painkillerReaction) {
			this.painkillerReaction = painkillerReaction;
			return this;
		}

		public Builder motionCausingPain(String motionCausingPain) {
			this.motionCausingPain = motionCausingPain;
			return this;
		}

		public Builder motorInterference(String motorInterference) {
			this.motorInterference = motorInterference;
			return this;
		}

		public Builder bodyPartUsagePossible(int bodyPartUsagePossible) {
			this.bodyPartUsagePossible = bodyPartUsagePossible;
			return this;
		}

		public Builder possibleWalkDistance(double possibleWalkDistance) {
			this.possibleWalkDistance = possibleWalkDistance;
			return this;
		}

		public Builder possibleWalkDuration(Time possibleWalkDuration) {
			this.possibleWalkDuration = possibleWalkDuration;
			return this;
		}

		public Builder weatherDependent(int weatherDependent) {
			this.weatherDependent = weatherDependent;
			return this;
		}

		public Builder cycleCorrelation(int cycleCorrelation) {
			this.cycleCorrelation = cycleCorrelation;
			return this;
		}

		public Builder outlet(double outlet) {
			this.outlet = outlet;
			return this;
		}

		public Builder availableTimeCons(Time availableTimeCons) {
			this.availableTimeCons = availableTimeCons;
			return this;
		}

		public Builder comment(String comment) {
			this.comment = comment;
			return this;
		}

		public AnamnesisBP build() {
			return new AnamnesisBP(this);
		}
	}

	private AnamnesisBP(Builder builder) {
		this.tierid = builder.tierid;
		this.anamnesisID = builder.anamnesisID;
		this.purpose = builder.purpose;
		this.keeping = builder.keeping;
		this.possesionsince = builder.possesionsince;
		this.origin = builder.origin;
		this.familystrchanges = builder.familystrchanges;
		this.abroadstays = builder.abroadstays;
		this.attitudeconspicuity = builder.attitudeconspicuity;
		this.injurys = builder.injurys;
		this.scars = builder.scars;
		this.infectiousDisease = builder.infectiousDisease;
		this.regularVaccinations = builder.regularVaccinations;
		this.breathing = builder.breathing;
		this.digestiveTract = builder.digestiveTract;
		this.endocrineSystem = builder.endocrineSystem;
		this.hyperthyroidism = builder.hyperthyroidism;
		this.pancreas = builder.pancreas;
		this.ZNS = builder.ZNS;
		this.epileptiformAttacks = builder.epileptiformAttacks;
		this.xray = builder.xray;
		this.medication = builder.medication;
		this.CT_MRT = builder.CT_MRT;
		this.mainproblem = builder.mainproblem;
		this.descrPatientOwner = builder.descrPatientOwner;
		this.wasUndertaken = builder.wasUndertaken;
		this.painSensitivity = builder.painSensitivity;
		this.patientHasPain = builder.patientHasPain;
		this.painkillerReaction = builder.painkillerReaction;
		this.motionCausingPain = builder.motionCausingPain;
		this.motorInterference = builder.motorInterference;
		this.bodyPartUsagePossible = builder.bodyPartUsagePossible;
		this.possibleWalkDistance = builder.possibleWalkDistance;
		this.possibleWalkDuration = builder.possibleWalkDuration;
		this.weatherDependent = builder.weatherDependent;
		this.cycleCorrelation = builder.cycleCorrelation;
		this.outlet = builder.outlet;
		this.availableTimeCons = builder.availableTimeCons;
		this.comment = builder.comment;
		this.circulation = builder.circulation;
	}
}
