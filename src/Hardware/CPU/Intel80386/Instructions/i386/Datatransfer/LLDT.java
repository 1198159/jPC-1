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
package Hardware.CPU.Intel80386.Instructions.i386.Datatransfer;

import Hardware.CPU.Intel80386.Exceptions.CPUException;
import Hardware.CPU.Intel80386.Instructions.Instruction;
import Hardware.CPU.Intel80386.Intel80386;
import Hardware.CPU.Intel80386.Operands.Operand;
import Hardware.CPU.Intel80386.Register.Segments.Descriptor;



public final class LLDT extends Instruction {

    private final Operand m_selector;
    
    public LLDT(Intel80386 cpu,
                Operand selector) {
        
        super(cpu);
        
        m_selector = selector;
    }
    
    @Override
    public void run() {
        
        if(m_cpu.CR.isInRealMode() || m_cpu.FLAGS.VM)
            throw CPUException.getInvalidOpcode();
        
        if(m_cpu.getCPL() != 0)
            throw CPUException.getGeneralProtectionFault(0);
        
        
        int selector = m_selector.getValue();
        
        if(m_cpu.isNullSelector(selector)) {
            
            m_cpu.LDT.setValid(false);
        }
        else {
            
            // The selector must point into GDT
            if(m_cpu.isReferencingLDT(selector))
                throw CPUException.getGeneralProtectionFault(selector & 0xfffc);
            
            // Load descriptor
            Descriptor desc;
            if((desc = m_cpu.getDescriptor(selector)) == null)
                throw CPUException.getGeneralProtectionFault(selector & 0xfffc);
            
            // Assure that this entry describes a LDT
            if(!desc.getTypeInfo().isLDT())
                throw CPUException.getGeneralProtectionFault(selector & 0xfffc);
            
            // Assure that the ldt is present
            if(!desc.isPresent())
                throw CPUException.getSegmentNotPresent(selector & 0xfffc);
            
            m_cpu.LDT.setSelector(selector);
            m_cpu.LDT.setBase(desc.getBase());
            m_cpu.LDT.setLimit(desc.getLimit());
            m_cpu.LDT.setValid(true);
        }
    }
    
    @Override
    public String toString() {
        
        return String.format("lldt %s", m_selector);
    }
}
