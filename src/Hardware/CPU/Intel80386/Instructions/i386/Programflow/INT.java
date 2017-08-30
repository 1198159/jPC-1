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
package Hardware.CPU.Intel80386.Instructions.i386.Programflow;

import Hardware.CPU.Intel80386.Instructions.Instruction;
import Hardware.CPU.Intel80386.Intel80386;
import Hardware.CPU.Intel80386.Operands.Operand;



public final class INT extends Instruction {

    private final Operand m_vector;
    
    public INT(Intel80386 cpu,
               Operand vector) {
        
        super(cpu);
        
        m_vector = vector;
    }
    
    @Override
    public void run() {
        
        if(m_cpu.CR.isInRealMode())
            m_cpu.handleRealModeInterrupt(m_vector.getValue());
        else
            m_cpu.handleProtectedModeInterrupt(m_vector.getValue(), Intel80386.INTERRUPT_SOFTWARE, null);
    }
    
    @Override
    public String toString() {
        
        return String.format("int %s", m_vector.toString());
    }
}
