/*
 * Copyright (C) 2017 h0MER247
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package Hardware.CPU.Intel80386.Condition.Conditions;

import Hardware.CPU.Intel80386.Condition.Condition;
import Hardware.CPU.Intel80386.Intel80386;



public final class ConditionNotParity implements Condition {
    
    private final Intel80386 m_cpu;
    
    public ConditionNotParity(Intel80386 cpu) {
        
        m_cpu = cpu;
    }

    @Override
    public boolean isTrue() {
        
        return !m_cpu.FLAGS.PF;
    }
    
    @Override
    public String toString() {
        
        return "np";
    }
}