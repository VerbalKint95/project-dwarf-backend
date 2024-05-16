package com.maligno.projectdwarf.springboot.fight;

import com.maligno.projectdwarf.springboot.model.Dwarf;

import lombok.Data;

@Data
public class Fight {
	
//attributes
	private Dwarf d1;
	private Dwarf d2;
	private FightResult result = new FightResult();
	
//constructors
	public Fight(Dwarf d1, Dwarf d2) {
		this.d1 = d1;
		this.d2 = d2;
	}
	
//operation
	
	public FightResult fight(int nbOfRound) {
		int round = 0;
		while (
				round++<nbOfRound
			&&	!isFinish()
				
		) {
			doRound();
		}
		if (isFinish()) {
			if (d1.getHealth() < 0 && d2.getHealth() < 0) {
				getResult().setMsg("No Contest, both KO");
				getResult().setStatus(FightStatus.TIE);
			} else {
				if (d1.getHealth() < 0 ) {
					switchD1D2();
				}
				getResult().setMsg("Won by KO");
				getResult().setStatus(FightStatus.WON);
			}
		}
		if (round == nbOfRound) {
			doFinishHim();
		}
		
		getResult().setDwarf1Id(d1.getId());
		getResult().setDwarf2Id(d2.getId());
		getResult().setDwarf1Name(d1.getName());
		getResult().setDwarf2Name(d2.getName());
		
		getResult().setNbRound(round);
		
		return result;
	}
	
	private void doRound() {
		put1stToAtkAsD1();
		d1Atk();
		if (!isFinish()) {
			switchD1D2();
			d1Atk();
		}
	}
	
	private void doFinishHim() {
		put1stToAtkAsD1();
		if (d1TryFinishHim()) {
			getResult().setStatus(FightStatus.WON);
		}
		if (!isFinish()) {
			switchD1D2();
			if (d1TryFinishHim()) {
				getResult().setStatus(FightStatus.WON);
			}
		}
		getResult().setStatus(FightStatus.TIE);
	}
	
	private void d1Atk() {
		if (d1.getHighAtk() > d1.getHighDef()) {
			//high atk
			if (d1.getHighAtk() > d2.getHighDef()) {
				d2.setHealth(d2.getHealth()-d1.getStrength()*d1.getStamina());
				
				d2.setStamina(
					Math.round(d2.getStamina() * 0.75)
				);
			} else {
				d2.setStamina(
					Math.round(d2.getStamina() * 0.85)
				);
			}
			d1.setStamina(
				Math.round(d1.getStamina() * 0.85)
			);
		} else {
			//low atk
			if (d1.getLowAtk() > d2.getLowDef()) {
				d2.setHealth(d2.getHealth()-d1.getStrength()*d1.getStamina());
				
				d2.setStamina(
					Math.round(d2.getStamina() * 0.70)
				);
			} else {
				d2.setStamina(
					Math.round(d2.getStamina() * 0.80)
				);
			}
			d1.setStamina(
				Math.round(d1.getStamina() * 0.80)
			);
		}
	}
	
	private boolean d1TryFinishHim() {
		switch (d1.getAtkSpe()) {
			case THEFORBIDENSPLINTER:
				d2.setHealth(0);
				getResult().setMsg("Won by throwing 'The Forbiden Splinter' in his opponent's toe");
				return true;
			case THEMEGAPUNCH:
				if (d1.getHighAtk() > d2.getHighDef()) {
					d2.setHealth(0);
					getResult().setMsg("Won by KO with 'The MEGA-PUNCH'");
					return true;
				}
				break;
			case THEABOMINABLESUBMISSION:
				if (d1.getLowAtk() > d2.getLowDef()) {
					d2.setHealth(0);
					getResult().setMsg("Won by submission with 'The ABOMINABLE SUBMISSION'");
					return true;
				}
				break;
		}
		return false;
	}
	
	private boolean isFinish() {
		return (getD1().getHealth() < 0 || getD2().getHealth() < 0);
	}
	
	
	private void put1stToAtkAsD1() {
		if (getD2().getAgility() * getD2().getStamina() > getD1().getAgility() * getD1().getStamina() ) {
			switchD1D2();
		}
	}
	
	private void switchD1D2() {
		var dtemp = getD2();
		setD2(getD1()) ;
		setD1(dtemp);
	}	

}
